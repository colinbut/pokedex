/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.core.service;

import com.mycompany.pokdedex.core.domain.Pokemon;

public interface PokemonService {

    void saveNewPokemon(Pokemon pokemon);

    Pokemon getPokemon(int id);

    void updatePokemon(Pokemon pokemon);

    void deletePokemon(int id);

}
