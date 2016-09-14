/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.db;

import com.mycompany.pokdedex.db.dto.PokemonTypeDto;
import com.mycompany.pokdedex.db.mapper.JDBIPokemonTypeDtoMapper;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface TypeDao {

    @SqlQuery("SELECT * FROM POKEMON_TYPE")
    @Mapper(JDBIPokemonTypeDtoMapper.class)
    List<PokemonTypeDto> fetch();
}
