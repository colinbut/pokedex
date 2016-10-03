/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.view;

import com.mycompany.pokedex.core.domain.Pokemon;
import io.dropwizard.views.View;

public class PokemonView extends View {

    private static final String TEMPLATE_FREEMARKER = "pokemon.ftl";
    private static final String TEMPLATE_MUSTACHE = "pokemon.mustache";

    private final Pokemon pokemon;

    public PokemonView(Pokemon pokemon) {
        super(TEMPLATE_MUSTACHE);
        this.pokemon = pokemon;
    }

    public Pokemon getPokemon(){
        return pokemon;
    }
}
