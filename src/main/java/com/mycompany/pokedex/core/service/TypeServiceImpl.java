/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.core.service;

import com.mycompany.pokedex.core.domain.Type;
import com.mycompany.pokedex.db.jdbi.TypeDaoJDBI;
import com.mycompany.pokedex.db.jdbi.dto.PokemonTypeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeServiceImpl implements TypeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TypeServiceImpl.class);

    private final TypeDaoJDBI typeDaoJDBI;

    private Map<Integer, String> typeMap = new HashMap<>();

    public TypeServiceImpl(TypeDaoJDBI typeDaoJDBI) {
        this.typeDaoJDBI = typeDaoJDBI;

        LOGGER.debug("Loading data from the database into memory");

        List<PokemonTypeDto> pokemonDtoList = typeDaoJDBI.fetch();
        for (PokemonTypeDto pokemonTypeDto : pokemonDtoList) {
            LOGGER.trace("Inserting data {} into memory", pokemonTypeDto);
            typeMap.put(pokemonTypeDto.getId(), pokemonTypeDto.getName());
        }
        LOGGER.debug("Finished loading data from the database into memory");
    }



    @Override
    public Type getTypeById(int id) {
        String pokemonType = typeMap.get(id);
        LOGGER.debug("Trying to get pokemon type for id: {}, {}", id, pokemonType);
        return Type.valueOf(pokemonType);
    }

    @Override
    public Type getTypeByName(String name) {
        return Type.valueOf(name);
    }
}
