/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.core.service;

import com.mycompany.pokdedex.db.PokemonAttackDao;
import com.mycompany.pokdedex.db.dto.PokemonAttackDto;

import java.util.ArrayList;
import java.util.List;

public class PokemonAttackServiceImpl implements PokemonAttackService {

    private final PokemonAttackDao pokemonAttackDao;

    public PokemonAttackServiceImpl(PokemonAttackDao pokemonAttackDao) {
        this.pokemonAttackDao = pokemonAttackDao;
    }


    @Override
    public List<Integer> fetch(int pokemonId) {
        List<Integer> attackIds = new ArrayList<>();
        List<PokemonAttackDto> pokemonAttackDtos = pokemonAttackDao.fetch(pokemonId);
        for (PokemonAttackDto pokemonAttackDto : pokemonAttackDtos) {
            attackIds.add(pokemonAttackDto.getAttackId());
        }
        return attackIds;
    }

}
