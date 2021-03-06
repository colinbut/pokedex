/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.converters;

import com.mycompany.pokedex.api.AttackRepresentation;
import com.mycompany.pokedex.api.PokemonRepresentation;
import com.mycompany.pokedex.api.TypeRepresentation;
import com.mycompany.pokedex.core.domain.Attack;
import com.mycompany.pokedex.core.domain.Pokemon;
import com.mycompany.pokedex.core.domain.Type;

import java.util.List;

public final class PokemonRepresentationDomainConverter {

    private PokemonRepresentationDomainConverter() {}

    public static Pokemon asDomain(PokemonRepresentation pokemonRepresentation) {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(pokemonRepresentation.getId());
        pokemon.setName(pokemonRepresentation.getName());
        pokemon.setHitPoints(pokemonRepresentation.getHitPoints());
        pokemon.setCombatPower(pokemonRepresentation.getCombatPower());

        List<Attack> attacks = AttackRepresentationDomainConverter.asDomainList(pokemonRepresentation.getAttacks());
        pokemon.setAttacks(attacks);

        pokemon.setType(Type.valueOf(pokemonRepresentation.getType().getTypeName()));
        return pokemon;
    }

    public static PokemonRepresentation asRepresentation(Pokemon pokemon) {
        PokemonRepresentation pokemonRepresentation = new PokemonRepresentation();
        pokemonRepresentation.setId(pokemon.getId());
        pokemonRepresentation.setName(pokemon.getName());
        pokemonRepresentation.setHitPoints(pokemon.getHitPoints());
        pokemonRepresentation.setCombatPower(pokemon.getCombatPower());

        List<AttackRepresentation> attackRepresentationList = AttackRepresentationDomainConverter.asRepresentationList(pokemon.getAttacks());
        pokemonRepresentation.setAttacks(attackRepresentationList);

        TypeRepresentation typeRepresentation = new TypeRepresentation();
        typeRepresentation.setTypeName(pokemon.getType().toString());
        pokemonRepresentation.setType(typeRepresentation);
        return pokemonRepresentation;
    }

}
