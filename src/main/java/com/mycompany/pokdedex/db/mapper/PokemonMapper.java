/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.db.mapper;

import com.mycompany.pokdedex.db.dto.PokemonDto;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PokemonMapper implements ResultSetMapper<PokemonDto> {

    @Override
    public PokemonDto map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        PokemonDto pokemonDto = new PokemonDto();
        pokemonDto.setId(resultSet.getInt("id"));
        return pokemonDto;
    }

}
