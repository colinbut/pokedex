/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.view;

import com.mycompany.pokedex.core.domain.Pokemon;
import io.dropwizard.views.View;

public class PokemonView extends View {

    private final Pokemon pokemon;

    public PokemonView(Pokemon pokemon) {
        super("pokemon.ftl");
        this.pokemon = pokemon;
    }

    public Pokemon getPokemon(){
        return pokemon;
    }
}
