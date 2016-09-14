/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.db;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface PokemonDao {

    @Deprecated
    @SqlUpdate("INSERT INTO POKEMON VALUES (:name, :hit_points, :combat_power, :type_id, :pokemon_attack_id)")
    void insert(@Bind("name") String name,
                @Bind("hit_points") int hitPoints,
                @Bind("combat_power") int combatPower,
                @Bind("type_id") int typeId,
                @Bind("pokemon_attack_id") int pokemonAttackId);


}
