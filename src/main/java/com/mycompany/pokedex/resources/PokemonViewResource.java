/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.resources;

import com.mycompany.pokedex.core.constants.DataAccessMethod;
import com.mycompany.pokedex.db.hibernate.dao.PokemonDaoHibernate;
import com.mycompany.pokedex.db.hibernate.transformer.PokemonEntityPokemonModelTransformer;
import com.mycompany.pokedex.db.jdbi.PokemonDaoJDBI;
import com.mycompany.pokedex.view.PokemonView;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/pokemonview/{id}")
@Produces(MediaType.TEXT_HTML)
public class PokemonViewResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonViewResource.class);

    private final PokemonDaoJDBI pokemonDaoJDBI;

    private final PokemonDaoHibernate pokemonDaoHibernate;

    private final String dataAccessMethod;

    public PokemonViewResource(PokemonDaoJDBI pokemonDaoJDBI, PokemonDaoHibernate pokemonDaoHibernate, String dataAccessMethod){
        this.pokemonDaoJDBI = pokemonDaoJDBI;
        this.pokemonDaoHibernate = pokemonDaoHibernate;
        this.dataAccessMethod = dataAccessMethod;
    }


    @GET
    @UnitOfWork
    public PokemonView getPokemon(@PathParam("id") int id) {
        if (dataAccessMethod.equals(DataAccessMethod.JDBI)) {
            //return new PokemonView(pokemonDaoJDBI.fetch(id));
            return null;
        } else if (dataAccessMethod.equals(DataAccessMethod.HIBERNATE)) {
            return new PokemonView(PokemonEntityPokemonModelTransformer.getModelFromEntity(pokemonDaoHibernate.fetch(id)));
        } else {
            LOGGER.error("Unknown data access method - server configuration error");
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }


}
