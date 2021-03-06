/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.db.jdbi;

import com.mycompany.pokedex.db.jdbi.dto.PokemonAttackDto;
import com.mycompany.pokedex.db.jdbi.mapper.JDBIPokemonAttackDtoMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(JDBIPokemonAttackDtoMapper.class)
public interface PokemonAttackDaoJDBI {

    /**
     * Can be simplified to just return list of Integers because all we're interested in
     * is the attack id (we don't care about the pokemon id)
     */

    @SqlQuery("SELECT * FROM POKEMON_ATTACK WHERE pokemon_id = :pokemon_id")
    List<PokemonAttackDto> fetch(@Bind("pokemon_id") int pokemonId);

}
