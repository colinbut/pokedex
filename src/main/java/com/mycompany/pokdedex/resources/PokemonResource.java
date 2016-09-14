/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.resources;

import com.mycompany.pokdedex.api.PokemonRepresentation;
import com.mycompany.pokdedex.core.domain.Pokemon;
import com.mycompany.pokdedex.core.service.PokemonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/pokemon/{id}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PokemonResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonResource.class);

    private final PokemonService pokemonService;

    public PokemonResource(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }


    @GET
    public PokemonRepresentation getPokemon(@PathParam("id") int id) {
        LOGGER.info("Retrieving pokemon data for pokemon with pokemon id: {}", id);
        return new PokemonRepresentation();
    }

    @PUT
    public void addPokemon() {
        pokemonService.saveNewPokemon(new Pokemon());
    }

    @POST
    public void updatePokemon() {
        throw new UnsupportedOperationException("Not Yet Implemented!");
    }

    @DELETE
    public void deletePokemon(@PathParam("id") int id) {
        throw new UnsupportedOperationException("Not Yet Implemented!");
    }

}
