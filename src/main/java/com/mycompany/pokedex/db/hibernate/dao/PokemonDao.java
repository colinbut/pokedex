/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.db.hibernate.dao;

import com.mycompany.pokedex.db.hibernate.entity.PokemonEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class PokemonDao extends AbstractDAO<PokemonEntity> {

    public PokemonDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void insert(PokemonEntity pokemonEntity) {
        persist(pokemonEntity);
    }

    public PokemonEntity fetch(int id) {
        return get(id);
    }

    public void update(PokemonEntity pokemonEntity) {
        persist(pokemonEntity);
    }

    public void delete(int id) {
        currentSession().delete(id);
    }

}
