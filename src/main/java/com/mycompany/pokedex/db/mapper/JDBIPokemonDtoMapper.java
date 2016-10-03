/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.db.mapper;

import com.mycompany.pokedex.db.dto.PokemonDto;
import org.apache.log4j.Logger;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBIPokemonDtoMapper implements ResultSetMapper<PokemonDto> {

    private static final Logger LOGGER = Logger.getLogger(JDBIPokemonDtoMapper.class);

    @Override
    public PokemonDto map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        PokemonDto pokemonDto = new PokemonDto();
        pokemonDto.setId(resultSet.getInt("id"));
        pokemonDto.setName(resultSet.getString("name"));
        pokemonDto.setHitPoints(resultSet.getInt("hit_points"));
        pokemonDto.setCombatPower(resultSet.getInt("combat_power"));
        pokemonDto.setPokemonTypeId(resultSet.getInt("type_id"));
        return pokemonDto;
    }

}
