/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.converters;

import com.mycompany.pokdedex.api.AttackRepresentation;
import com.mycompany.pokdedex.api.PokemonRepresentation;
import com.mycompany.pokdedex.api.TypeRepresentation;
import com.mycompany.pokdedex.core.domain.Attack;
import com.mycompany.pokdedex.core.domain.Pokemon;
import com.mycompany.pokdedex.core.domain.Type;

import java.util.List;

public final class PokemonRepresentationDomainTransformer {

    private PokemonRepresentationDomainTransformer() {}

    public static Pokemon asDomain(PokemonRepresentation pokemonRepresentation) {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(pokemonRepresentation.getId());
        pokemon.setName(pokemonRepresentation.getName());
        pokemon.setHitPoints(pokemonRepresentation.getHitPoints());
        pokemon.setCombatPower(pokemonRepresentation.getCombatPower());

        List<Attack> attacks = AttackRepresentationDomainTransformer.asDomainList(pokemonRepresentation.getAttacks());
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

        List<AttackRepresentation> attackRepresentationList = AttackRepresentationDomainTransformer.asRepresentationList(pokemon.getAttacks());
        pokemonRepresentation.setAttacks(attackRepresentationList);

        TypeRepresentation typeRepresentation = new TypeRepresentation();
        typeRepresentation.setTypeName(pokemon.getType().toString());
        pokemonRepresentation.setType(typeRepresentation);
        return pokemonRepresentation;
    }

}
