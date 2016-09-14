/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.db;

import com.mycompany.pokdedex.db.dto.PokemonTypeDto;
import org.skife.jdbi.v2.sqlobject.SqlQuery;

import java.util.List;

public interface TypeDao {

    @SqlQuery("SELECT * FROM POKEMON_TYPE")
    List<PokemonTypeDto> fetch();
}
