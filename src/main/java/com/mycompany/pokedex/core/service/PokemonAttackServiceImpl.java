/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.core.service;

import com.mycompany.pokedex.db.jdbi.PokemonAttackDaoJDBI;
import com.mycompany.pokedex.db.jdbi.dto.PokemonAttackDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PokemonAttackServiceImpl implements PokemonAttackService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonAttackServiceImpl.class);

    private final PokemonAttackDaoJDBI pokemonAttackDaoJDBI;

    public PokemonAttackServiceImpl(PokemonAttackDaoJDBI pokemonAttackDaoJDBI) {
        this.pokemonAttackDaoJDBI = pokemonAttackDaoJDBI;
    }


    @Override
    public List<Integer> fetch(int pokemonId) {
        LOGGER.info("Fetching list of pokemon attacks");
        List<Integer> attackIds = new ArrayList<>();
        List<PokemonAttackDto> pokemonAttackDtos = pokemonAttackDaoJDBI.fetch(pokemonId);
        for (PokemonAttackDto pokemonAttackDto : pokemonAttackDtos) {
            attackIds.add(pokemonAttackDto.getAttackId());
        }
        LOGGER.debug("Fetched list of pokemon attack ids: {}", attackIds);
        return attackIds;
    }

}
