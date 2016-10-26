/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.core;


import com.mycompany.pokedex.db.jdbi.TypeDaoJDBI;
import com.mycompany.pokedex.db.jdbi.dto.PokemonTypeDto;
import com.mycompany.pokedex.resources.PokemonApiResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokemonTypeInternalMap {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonApiResource.class);

    private final TypeDaoJDBI typeDaoJDBI;

    private Map<Integer, String> typeMap = new HashMap<>();

    public PokemonTypeInternalMap(TypeDaoJDBI typeDaoJDBI) {
        this.typeDaoJDBI = typeDaoJDBI;
        initializePokemonTypeMap();
    }

    private void initializePokemonTypeMap() {
        List<PokemonTypeDto> pokemonDtoList = typeDaoJDBI.fetch();
        for (PokemonTypeDto pokemonTypeDto : pokemonDtoList) {
            LOGGER.trace("Inserting data {} into memory", pokemonTypeDto);
            typeMap.put(pokemonTypeDto.getId(), pokemonTypeDto.getName());
        }
        LOGGER.debug("Finished loading pokemon type data from the database into memory");
    }

    public String getPokemonType(int id) {
        return typeMap.get(id);
    }

}
