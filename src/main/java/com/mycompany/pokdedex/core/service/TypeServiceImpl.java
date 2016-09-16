/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.core.service;

import com.mycompany.pokdedex.core.domain.Type;
import com.mycompany.pokdedex.db.TypeDao;
import com.mycompany.pokdedex.db.dto.PokemonTypeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeServiceImpl implements TypeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TypeServiceImpl.class);

    private final TypeDao typeDao;

    private Map<Integer, String> typeMap = new HashMap<>();

    public TypeServiceImpl(TypeDao typeDao) {
        this.typeDao = typeDao;

        LOGGER.debug("Loading data from the database into memory");

        List<PokemonTypeDto> pokemonDtoList = typeDao.fetch();
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
