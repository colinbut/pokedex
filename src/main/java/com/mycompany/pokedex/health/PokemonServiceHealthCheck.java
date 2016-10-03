/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.health;

import com.codahale.metrics.health.HealthCheck;
import com.mycompany.pokedex.core.service.PokemonService;

public class PokemonServiceHealthCheck extends HealthCheck {

    private PokemonService pokemonService;

    public PokemonServiceHealthCheck(PokemonService pokemonService){
        this.pokemonService = pokemonService;
    }


    @Override
    protected Result check() throws Exception {
        if (pokemonService.getPokemon(1) != null) {
            return Result.healthy();
        }
        return Result.unhealthy("Unable to retrieve an example (1st) pokemon from Pokemon Service");
    }
}
