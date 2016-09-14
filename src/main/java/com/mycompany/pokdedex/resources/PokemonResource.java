/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.resources;

import com.mycompany.pokdedex.api.PokemonRepresentation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/pokemon")
@Produces(MediaType.APPLICATION_JSON)
public class PokemonResource {

    @GET
    public PokemonRepresentation getPokemon() {
        return null;
    }

}
