/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.resources;

import com.mycompany.pokedex.core.service.PokemonService;
import com.mycompany.pokedex.db.hibernate.dao.PokemonDaoHibernate;
import com.mycompany.pokedex.db.hibernate.transformer.PokemonEntityPokemonModelTransformer;
import com.mycompany.pokedex.view.PokemonView;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/pokemonview/{id}")
@Produces(MediaType.TEXT_HTML)
public class PokemonViewResource {

    private final PokemonService pokemonService;

    private final PokemonDaoHibernate pokemonDaoHibernate;

    public PokemonViewResource(PokemonService pokemonService, PokemonDaoHibernate pokemonDaoHibernate){
        this.pokemonService = pokemonService;
        this.pokemonDaoHibernate = pokemonDaoHibernate;
    }


    @GET
    @UnitOfWork
    public PokemonView getPokemon(@PathParam("id") int id) {
        //return new PokemonView(pokemonService.getPokemon(id));
        return new PokemonView(PokemonEntityPokemonModelTransformer.getModelFromEntity(pokemonDaoHibernate.fetch(id)));
    }


}
