/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.resources;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import com.mycompany.pokedex.api.PokemonRepresentation;
import com.mycompany.pokedex.converters.PokemonRepresentationDomainConverter;
import com.mycompany.pokedex.core.constants.DataAccessMethod;
import com.mycompany.pokedex.core.domain.Pokemon;
import com.mycompany.pokedex.db.hibernate.dao.PokemonDaoHibernate;
import com.mycompany.pokedex.db.hibernate.transformer.PokemonEntityPokemonModelTransformer;
import com.mycompany.pokedex.db.jdbi.PokemonDaoJDBI;
import com.mycompany.pokedex.db.jdbi.dto.PokemonDto;
import com.mycompany.pokedex.db.jdbi.transformer.PokemonDtoTransformer;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.caching.CacheControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.concurrent.TimeUnit;

@Path("/pokemon/{id}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PokemonApiResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonApiResource.class);

    private final PokemonDaoJDBI pokemonDaoJDBI;
    private final PokemonDaoHibernate pokemonDaoHibernate;

    private final String dataAccessMethod;

    private final PokemonDtoTransformer pokemonDtoTransformer;

    public PokemonApiResource(PokemonDaoJDBI pokemonDaoJDBI,
                              PokemonDaoHibernate pokemonDaoHibernate,
                              PokemonDtoTransformer pokemonDtoTransformer,
                              String dataAccessMethod) {
        this.pokemonDaoJDBI = pokemonDaoJDBI;
        this.pokemonDaoHibernate = pokemonDaoHibernate;
        this.pokemonDtoTransformer = pokemonDtoTransformer;

        this.dataAccessMethod = dataAccessMethod;
    }


    @GET
    @Timed(name = "showAll-timed-get")
    @Metered(name = "showAll-metered-get")
    @ExceptionMetered
    @CacheControl(maxAge = 12, maxAgeUnit = TimeUnit.HOURS)
    @UnitOfWork
    public PokemonRepresentation getPokemon(@PathParam("id") int id) {
        LOGGER.info("Retrieving pokemon data for pokemon with pokemon id: {}", id);

        Pokemon pokemon;
        if (dataAccessMethod.equals(DataAccessMethod.JDBI)) {
            PokemonDto pokemonDto = pokemonDaoJDBI.fetch(id);
            pokemon = pokemonDtoTransformer.transformDtoToDomain(pokemonDto);
        } else if (dataAccessMethod.equals(DataAccessMethod.HIBERNATE)) {
            pokemon = PokemonEntityPokemonModelTransformer.getModelFromEntity(pokemonDaoHibernate.fetch(id));
        } else {
            LOGGER.error("Unknown data access method - server configuration error");
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        if (pokemon == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        LOGGER.debug("Got pokemon {} from the PokemonService", pokemon);
        PokemonRepresentation pokemonRepresentation = PokemonRepresentationDomainConverter.asRepresentation(pokemon);
        LOGGER.info("Retrieved pokemon data {} for pokemon with id: {}", pokemonRepresentation, id);
        return pokemonRepresentation;
    }

    @PUT
    @Timed(name = "showAll-timed-put")
    @Metered(name = "showAll-metered-put")
    @ExceptionMetered
    @UnitOfWork
    public Response addPokemon(@PathParam("id") int id, @NotNull @Valid PokemonRepresentation pokemonRepresentation) {
        LOGGER.info("Adding pokemon {}", pokemonRepresentation);
        Pokemon pokemon = PokemonRepresentationDomainConverter.asDomain(pokemonRepresentation);

        if (dataAccessMethod.equals(DataAccessMethod.JDBI)) {
            pokemonDaoJDBI.insert(pokemon.getName(), pokemon.getHitPoints(), pokemon.getCombatPower(), 1);
        } else if (dataAccessMethod.equals(DataAccessMethod.HIBERNATE)) {
            pokemonDaoHibernate.insert(PokemonEntityPokemonModelTransformer.getEntityFromModel(pokemon));
        } else {
            LOGGER.error("Unknown data access method - server configuration error");
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        LOGGER.info("Successfully added new pokemon: {}", id);
        return Response.created(UriBuilder.fromResource(PokemonApiResource.class).build(id)).build();
    }

    @POST
    @Timed(name = "showAll-timed-post")
    @Metered(name = "showAll-metered-post")
    @ExceptionMetered
    @UnitOfWork
    public Response updatePokemon(@PathParam("id") int id, @NotNull @Valid PokemonRepresentation pokemonRepresentation) {
        Pokemon pokemon = PokemonRepresentationDomainConverter.asDomain(pokemonRepresentation);

        if (dataAccessMethod.equals(DataAccessMethod.JDBI)) {

        } else if (dataAccessMethod.equals(DataAccessMethod.HIBERNATE))  {
            pokemonDaoHibernate.update(PokemonEntityPokemonModelTransformer.getEntityFromModel(pokemon));
        } else {
            LOGGER.error("Unknown data access method - server configuration error");
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        return Response.created(UriBuilder.fromResource(PokemonApiResource.class).build(id)).build();
    }

    @DELETE
    @Timed(name = "showAll-timed-delete")
    @Metered(name = "showAll-metered-delete")
    @ExceptionMetered
    @UnitOfWork
    public Response deletePokemon(@PathParam("id") int id) {
        LOGGER.info("Deleting pokemon: {}", id);

        if (dataAccessMethod.equals(DataAccessMethod.JDBI)) {
            pokemonDaoJDBI.delete(id);
        } else if (dataAccessMethod.equals(DataAccessMethod.HIBERNATE))  {
            pokemonDaoHibernate.delete(id);
        } else {
            LOGGER.error("Unknown data access method - server configuration error");
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        LOGGER.info("Successfully deleted pokemon: {}", id);
        return Response.created(UriBuilder.fromResource(PokemonApiResource.class).build(id)).build();
    }


}
