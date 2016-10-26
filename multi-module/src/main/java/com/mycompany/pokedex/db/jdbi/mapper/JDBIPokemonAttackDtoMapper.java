/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.db.jdbi.mapper;

import com.mycompany.pokedex.db.jdbi.dto.PokemonAttackDto;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBIPokemonAttackDtoMapper implements ResultSetMapper<PokemonAttackDto> {

    @Override
    public PokemonAttackDto map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        PokemonAttackDto pokemonAttackDto = new PokemonAttackDto();
        pokemonAttackDto.setPokemonId(resultSet.getInt("pokemon_id"));
        pokemonAttackDto.setAttackId(resultSet.getInt("attack_id"));
        return pokemonAttackDto;
    }

}
