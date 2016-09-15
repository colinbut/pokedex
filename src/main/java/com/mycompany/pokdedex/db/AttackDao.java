/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.db;

import com.mycompany.pokdedex.db.dto.AttackDto;
import com.mycompany.pokdedex.db.mapper.JDBIAttackDtoMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(JDBIAttackDtoMapper.class)
public interface AttackDao {

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
