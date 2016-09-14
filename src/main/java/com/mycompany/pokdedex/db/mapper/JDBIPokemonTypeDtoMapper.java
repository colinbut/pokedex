/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.db.mapper;

import com.mycompany.pokdedex.db.dto.PokemonTypeDto;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBIPokemonTypeDtoMapper implements ResultSetMapper<PokemonTypeDto> {

    @Override
    public PokemonTypeDto map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        PokemonTypeDto pokemonTypeDto = new PokemonTypeDto();
        pokemonTypeDto.setId(resultSet.getInt("id"));
        pokemonTypeDto.setName(resultSet.getString("name"));
        return pokemonTypeDto;
    }
}
