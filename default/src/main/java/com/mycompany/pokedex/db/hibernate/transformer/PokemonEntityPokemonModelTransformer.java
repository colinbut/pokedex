/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.db.hibernate.transformer;

import com.mycompany.pokedex.core.domain.Pokemon;
import com.mycompany.pokedex.db.hibernate.entity.PokemonEntity;


public final class PokemonEntityPokemonModelTransformer {

    private PokemonEntityPokemonModelTransformer() {}

    public static Pokemon getModelFromEntity(PokemonEntity pokemonEntity) {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(pokemonEntity.getId());
        pokemon.setName(pokemonEntity.getName());
        pokemon.setHitPoints(pokemonEntity.getHitPoints());
        pokemon.setCombatPower(pokemonEntity.getCombatPower());
        pokemon.setType(PokemonTypeEntityPokemonTypeTransformer.getModelFromEntity(pokemonEntity.getPokemonType()));
        pokemon.setAttacks(AttackEntityAttackModelTransformer.getModelListFromEntitySet(pokemonEntity.getAttacks()));
        return pokemon;
    }

    public static PokemonEntity getEntityFromModel(Pokemon pokemon) {
        PokemonEntity pokemonEntity = new PokemonEntity();
        pokemonEntity.setId(pokemon.getId());
        pokemonEntity.setName(pokemon.getName());
        pokemonEntity.setHitPoints(pokemon.getHitPoints());
        pokemonEntity.setCombatPower(pokemon.getCombatPower());
        pokemonEntity.setPokemonType(PokemonTypeEntityPokemonTypeTransformer.getEntityFromModel(pokemon.getType()));
        pokemonEntity.setAttacks(AttackEntityAttackModelTransformer.getEntitySetFromModelList(pokemon.getAttacks()));
        return pokemonEntity;
    }
}
