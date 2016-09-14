/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.db;

import com.mycompany.pokdedex.db.dto.AttackDto;
import com.mycompany.pokdedex.db.mapper.AttackMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(AttackMapper.class)
public interface AttackDao {

    @SqlQuery("SELECT id, name FROM ATTACK WHERE ID = :id")
    AttackDto fetch(@Bind("id") int id);

}
