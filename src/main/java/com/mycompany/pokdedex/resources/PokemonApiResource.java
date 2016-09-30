/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.resources;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import com.mycompany.pokdedex.api.PokemonRepresentation;
import com.mycompany.pokdedex.converters.PokemonRepresentationDomainConverter;
import com.mycompany.pokdedex.core.domain.Pokemon;
import com.mycompany.pokdedex.core.service.PokemonService;
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

    private final PokemonService pokemonService;


    public PokemonApiResource(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }


    @GET
    @Timed
    @Metered
    @ExceptionMetered
    @CacheControl(maxAge = 12, maxAgeUnit = TimeUnit.HOURS)
    public PokemonRepresentation getPokemon(@PathParam("id") int id) {
        LOGGER.info("Retrieving pokemon data for pokemon with pokemon id: {}", id);
        Pokemon pokemon = pokemonService.getPokemon(id);
        if (pokemon == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        LOGGER.debug("Got pokemon {} from the PokemonService", pokemon);
        PokemonRepresentation pokemonRepresentation = PokemonRepresentationDomainConverter.asRepresentation(pokemon);
        LOGGER.info("Retrieved pokemon data {} for pokemon with id: {}", pokemonRepresentation, id);
        return pokemonRepresentation;
    }

    @PUT
    @Timed
    @Metered
    @ExceptionMetered
    public Response addPokemon(@PathParam("id") int id, @NotNull @Valid PokemonRepresentation pokemonRepresentation) {
        LOGGER.info("Adding pokemon {}", pokemonRepresentation);
        Pokemon pokemon = PokemonRepresentationDomainConverter.asDomain(pokemonRepresentation);
        pokemonService.saveNewPokemon(pokemon);
        LOGGER.info("Successfully added new pokemon: {}", id);
        return Response.created(UriBuilder.fromResource(PokemonApiResource.class).build(id)).build();
    }

    @POST
    @Timed
    @Metered
    @ExceptionMetered
    public Response updatePokemon(@PathParam("id") int id, @NotNull @Valid PokemonRepresentation pokemonRepresentation) {
        Pokemon pokemon = PokemonRepresentationDomainConverter.asDomain(pokemonRepresentation);
        pokemonService.updatePokemon(pokemon);
        return Response.created(UriBuilder.fromResource(PokemonApiResource.class).build(id)).build();
    }

    @DELETE
    @Timed
    @Metered
    @ExceptionMetered
    public Response deletePokemon(@PathParam("id") int id) {
        LOGGER.info("Deleting pokemon: {}", id);
        pokemonService.deletePokemon(id);
        LOGGER.info("Successfully deleted pokemon: {}", id);
        return Response.created(UriBuilder.fromResource(PokemonApiResource.class).build(id)).build();
    }

}
