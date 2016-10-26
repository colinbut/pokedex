/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.db.hibernate.transformer;

import com.mycompany.pokedex.core.domain.Type;
import com.mycompany.pokedex.db.hibernate.entity.PokemonTypeEntity;

public class PokemonTypeEntityPokemonTypeTransformer {

    public static Type getModelFromEntity(PokemonTypeEntity pokemonTypeEntity) {
        return Type.valueOf(pokemonTypeEntity.getName());
    }

    public static PokemonTypeEntity getEntityFromModel(Type type) {
        PokemonTypeEntity pokemonTypeEntity = new PokemonTypeEntity();
        pokemonTypeEntity.setName(type.name());
        return pokemonTypeEntity;
    }

}
