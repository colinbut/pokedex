/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.db.jdbi;

import com.mycompany.pokedex.db.jdbi.dto.AttackDto;
import com.mycompany.pokedex.db.jdbi.mapper.JDBIAttackDtoMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(JDBIAttackDtoMapper.class)
public interface AttackDaoJDBI {

    /**
     * Using in-memory maps so no need to query to DB
     *
     * @param id the id of the attack entry
     * @return {@link AttackDto}
     */
    @Deprecated
    @SqlQuery("SELECT name FROM ATTACK WHERE ID = :id")
    AttackDto fetch(@Bind("id") int id);

    @SqlQuery("SELECT * FROM ATTACK")
    List<AttackDto> fetchList();

}
