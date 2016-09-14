/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.core.service;

import com.mycompany.pokdedex.core.domain.Pokemon;
import com.mycompany.pokdedex.db.PokemonDao;
import com.mycompany.pokdedex.db.dto.PokemonDto;

public class PokemonServiceImpl implements PokemonService {

    private final PokemonDao pokemonDao;
    private final AttackService attackService;
    private final TypeService typeService;

    public PokemonServiceImpl(PokemonDao pokemonDao, AttackService attackService, TypeService typeService) {
        this.pokemonDao = pokemonDao;
        this.attackService = attackService;
        this.typeService = typeService;
    }


    @Override
    public void saveNewPokemon(Pokemon pokemon) {
        pokemonDao.insert(pokemon.getName(), pokemon.getHitPoints(), pokemon.getCombatPower(), 1);
    }

    @Override
    public Pokemon getPokemon(int id) {
        PokemonDto pokemonDto = pokemonDao.fetch(id);
        typeService.getTypeById(pokemonDto.getPokemonTypeId());
        return null;
    }

    @Override
    public void updatePokemon(Pokemon pokemon) {
        throw new UnsupportedOperationException("Not Yet Implemented!");
    }

    @Override
    public void deletePokemon(int id) {
        pokemonDao.delete(id);
    }
}
