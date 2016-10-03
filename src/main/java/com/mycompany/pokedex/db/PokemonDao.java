/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.db;

import com.mycompany.pokedex.db.dto.PokemonDto;
import com.mycompany.pokedex.db.mapper.JDBIPokemonDtoMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

public interface PokemonDao {

    @SqlUpdate("INSERT INTO POKEMON (name, hit_points, combat_power, type_id) " +
        "VALUES (:name, :hit_points, :combat_power, :type_id)")
    void insert(@Bind("name") String name,
                @Bind("hit_points") int hitPoints,
                @Bind("combat_power") int combatPower,
                @Bind("type_id") int typeId);


    @SqlQuery("SELECT * FROM POKEMON WHERE ID = :id")
    @Mapper(JDBIPokemonDtoMapper.class)
    PokemonDto fetch(@Bind("id") int id);

    @SqlUpdate("UPDATE POKEMON SET name = :name, hit_points = :hit_points, combat_power = :combat_power, type_id = :type_id")
    void update(@Bind("name") String name,
                @Bind("hit_points") int hitPoints,
                @Bind("combat_power") int combatPower,
                @Bind("type_id") int typeId);

    @SqlUpdate("DELETE FROM POKEMON WHERE ID = :id")
    void delete(@Bind("id") int id);

}
