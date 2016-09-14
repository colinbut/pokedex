/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.core.service;

import com.mycompany.pokdedex.core.domain.Pokemon;
import com.mycompany.pokdedex.db.PokemonDao;

public class PokemonServiceImpl implements PokemonService {

    private final PokemonDao pokemonDao;

    public PokemonServiceImpl(PokemonDao pokemonDao) {
        this.pokemonDao = pokemonDao;
    }


    @Override
    public void saveNewPokemon(Pokemon pokemon) {
        pokemonDao.insert(pokemon.getName(), pokemon.getHitPoints(), pokemon.getCombatPower(), 1, 1);
    }

    @Override
    public Pokemon getPokemon(int id) {
        return null;
    }
}
