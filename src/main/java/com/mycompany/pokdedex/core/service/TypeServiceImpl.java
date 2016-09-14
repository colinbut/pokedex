/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.core.service;

import com.mycompany.pokdedex.core.domain.Type;
import com.mycompany.pokdedex.db.TypeDao;
import com.mycompany.pokdedex.db.dto.PokemonTypeDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeServiceImpl implements TypeService {

    private final TypeDao typeDao;

    private Map<Integer, String> typeMap = new HashMap<>();

    public TypeServiceImpl(TypeDao typeDao) {
        this.typeDao = typeDao;

        List<PokemonTypeDto> pokemonDtoList = typeDao.fetch();
        for (PokemonTypeDto pokemonTypeDto : pokemonDtoList) {
            typeMap.put(pokemonTypeDto.getId(), pokemonTypeDto.getName());
        }
    }



    @Override
    public Type getTypeById(int id) {
        String pokemonType = typeMap.get(id);
        return Type.valueOf(pokemonType);
    }

    @Override
    public Type getTypeByName(String name) {
        return Type.valueOf(name);
    }
}
