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
import com.mycompany.pokedex.core.domain.Attack;
import com.mycompany.pokedex.core.domain.Pokemon;
import com.mycompany.pokedex.core.domain.Type;
import com.mycompany.pokedex.db.hibernate.dao.PokemonDaoHibernate;
import com.mycompany.pokedex.db.jdbi.AttackDaoJDBI;
import com.mycompany.pokedex.db.jdbi.PokemonAttackDaoJDBI;
import com.mycompany.pokedex.db.jdbi.PokemonDaoJDBI;
import com.mycompany.pokedex.db.jdbi.TypeDaoJDBI;
import com.mycompany.pokedex.db.jdbi.dto.AttackDto;
import com.mycompany.pokedex.db.jdbi.dto.PokemonAttackDto;
import com.mycompany.pokedex.db.jdbi.dto.PokemonDto;
import com.mycompany.pokedex.db.jdbi.dto.PokemonTypeDto;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Path("/pokemon/{id}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PokemonApiResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonApiResource.class);

    private final PokemonDaoJDBI pokemonDaoJDBI;
    private final TypeDaoJDBI typeDaoJDBI;
    private final AttackDaoJDBI attackDaoJDBI;
    private final PokemonAttackDaoJDBI pokemonAttackDaoJDBI;

    private final PokemonDaoHibernate pokemonDaoHibernate;

    private Map<Integer, String> typeMap = new HashMap<>();
    private Map<Integer, AttackDto> attackMap = new HashMap<>();


    public PokemonApiResource(PokemonDaoJDBI pokemonDaoJDBI,
                              PokemonDaoHibernate pokemonDaoHibernate,
                              TypeDaoJDBI typeDaoJDBI,
                              AttackDaoJDBI attackDaoJDBI,
                              PokemonAttackDaoJDBI pokemonAttackDaoJDBI) {
        this.pokemonDaoJDBI = pokemonDaoJDBI;
        this.pokemonDaoHibernate = pokemonDaoHibernate;
        this.typeDaoJDBI = typeDaoJDBI;
        this.attackDaoJDBI = attackDaoJDBI;
        this.pokemonAttackDaoJDBI = pokemonAttackDaoJDBI;

        initializePokemonTypeMap();
        initializeAttackMap();
    }

    private void initializePokemonTypeMap() {
        List<PokemonTypeDto> pokemonDtoList = typeDaoJDBI.fetch();
        for (PokemonTypeDto pokemonTypeDto : pokemonDtoList) {
            LOGGER.trace("Inserting data {} into memory", pokemonTypeDto);
            typeMap.put(pokemonTypeDto.getId(), pokemonTypeDto.getName());
        }
        LOGGER.debug("Finished loading pokemon type data from the database into memory");
    }

    private void initializeAttackMap() {
        List<AttackDto> attackDtoList = attackDaoJDBI.fetchList();
        for (AttackDto attackDto : attackDtoList) {
            LOGGER.trace("Inserted {} into memory map", attackDto);
            attackMap.put(attackDto.getId(), attackDto);
        }
        LOGGER.debug("Finished loading data from the database into memory");
    }


    @GET
    @Timed(name = "showAll-timed-get")
    @Metered(name = "showAll-metered-get")
    @ExceptionMetered
    @CacheControl(maxAge = 12, maxAgeUnit = TimeUnit.HOURS)
    @UnitOfWork
    public PokemonRepresentation getPokemon(@PathParam("id") int id) {
        LOGGER.info("Retrieving pokemon data for pokemon with pokemon id: {}", id);

        PokemonDto pokemonDto = pokemonDaoJDBI.fetch(id);
        Pokemon pokemon = new PokemonDtoTransformer().transformDtoToDomain(pokemonDto);

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
        pokemonDaoJDBI.insert(pokemon.getName(), pokemon.getHitPoints(), pokemon.getCombatPower(), 1);
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
        return Response.created(UriBuilder.fromResource(PokemonApiResource.class).build(id)).build();
    }

    @DELETE
    @Timed(name = "showAll-timed-delete")
    @Metered(name = "showAll-metered-delete")
    @ExceptionMetered
    @UnitOfWork
    public Response deletePokemon(@PathParam("id") int id) {
        LOGGER.info("Deleting pokemon: {}", id);
        pokemonDaoJDBI.delete(id);
        LOGGER.info("Successfully deleted pokemon: {}", id);
        return Response.created(UriBuilder.fromResource(PokemonApiResource.class).build(id)).build();
    }

    private Type getTypeById(int id) {
        String pokemonType = typeMap.get(id);
        LOGGER.debug("Trying to get pokemon type for id: {}, {}", id, pokemonType);
        return Type.valueOf(pokemonType);
    }

    private List<Integer> fetchListOfPokemonAttacks(int pokemonId) {
        LOGGER.info("Fetching list of pokemon attacks");
        List<Integer> attackIds = new ArrayList<>();
        List<PokemonAttackDto> pokemonAttackDtos = pokemonAttackDaoJDBI.fetch(pokemonId);
        for (PokemonAttackDto pokemonAttackDto : pokemonAttackDtos) {
            attackIds.add(pokemonAttackDto.getAttackId());
        }
        LOGGER.debug("Fetched list of pokemon attack ids: {}", attackIds);
        return attackIds;
    }

    private class PokemonDtoTransformer {

        public Pokemon transformDtoToDomain(PokemonDto pokemonDto) {
            Pokemon pokemon = new Pokemon();
            pokemon.setId(pokemonDto.getId());
            pokemon.setName(pokemonDto.getName());
            pokemon.setHitPoints(pokemonDto.getHitPoints());
            pokemon.setCombatPower(pokemonDto.getCombatPower());
            pokemon.setType(transformType(pokemonDto.getPokemonTypeId()));
            pokemon.setAttacks(transformAttacks(pokemonDto));
            return pokemon;
        }

        private Type transformType(int typeId) {
            return getTypeById(typeId);
        }

        private List<Attack> transformAttacks(PokemonDto pokemonDto) {
            List<Integer> attackIds = fetchListOfPokemonAttacks(pokemonDto.getId());
            List<Attack> attacks = new ArrayList<>();
            for (Integer attackId : attackIds) {
                LOGGER.debug("Getting list of attacks for pokemon with id: {}", pokemonDto.getId());
                AttackDto attackDto = attackMap.get(attackId);
                Attack attack = new AttackDtoTransformer().transformDtoToDomain(attackDto);
                attacks.add(attack);
            }
            LOGGER.debug("Got pokemon attacks {} for pokemon with id: {}", attacks, pokemonDto.getId());
            return attacks;
        }
    }


    private class AttackDtoTransformer {

        public Attack transformDtoToDomain(AttackDto attackDto) {
            Attack attack = new Attack();
            attack.setName(attackDto.getAttackName());
            attack.setPower(attackDto.getPower());
            attack.setAccuracy(attackDto.getAccuracy());
            attack.setType(getTypeById(attackDto.getType()));
            return attack;
        }

    }

}
