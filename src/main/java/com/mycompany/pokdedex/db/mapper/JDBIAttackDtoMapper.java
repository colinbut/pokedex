/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.db.mapper;

import com.mycompany.pokdedex.db.dto.AttackDto;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBIAttackDtoMapper implements ResultSetMapper<AttackDto> {

    @Override
    public AttackDto map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        AttackDto attackDto = new AttackDto();
        attackDto.setId(resultSet.getInt("id"));
        attackDto.setAttackName(resultSet.getString("name"));
        return attackDto;
    }

}