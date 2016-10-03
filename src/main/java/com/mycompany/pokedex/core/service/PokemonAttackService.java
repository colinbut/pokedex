/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.core.service;

import java.util.List;

public interface PokemonAttackService {

    List<Integer> fetch(int pokemonId);
}
