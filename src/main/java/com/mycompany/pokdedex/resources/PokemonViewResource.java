/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.resources;

import com.mycompany.pokdedex.core.service.PokemonService;
import com.mycompany.pokdedex.view.PokemonView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/pokemonview/{id}")
@Produces(MediaType.TEXT_HTML)
public class PokemonViewResource {

    private final PokemonService pokemonService;

    public PokemonViewResource(PokemonService pokemonService){
        this.pokemonService = pokemonService;
    }


    @GET
    public PokemonView getPokemon(@PathParam("id") int id) {
        return new PokemonView(pokemonService.getPokemon(id));
    }


}
