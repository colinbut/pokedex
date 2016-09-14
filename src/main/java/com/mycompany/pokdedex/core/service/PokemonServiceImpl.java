/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.core.service;

import com.mycompany.pokdedex.core.domain.Attack;
import com.mycompany.pokdedex.core.domain.Pokemon;
import com.mycompany.pokdedex.core.domain.Type;
import com.mycompany.pokdedex.db.PokemonDao;
import com.mycompany.pokdedex.db.dto.PokemonDto;

import java.util.ArrayList;
import java.util.List;

public class PokemonServiceImpl implements PokemonService {

    private final PokemonDao pokemonDao;
    private final AttackService attackService;
    private final TypeService typeService;
    private final PokemonAttackService pokemonAttackService;

    public PokemonServiceImpl(PokemonDao pokemonDao, AttackService attackService, TypeService typeService, PokemonAttackService pokemonAttackService) {
        this.pokemonDao = pokemonDao;
        this.attackService = attackService;
        this.typeService = typeService;
        this.pokemonAttackService = pokemonAttackService;
    }


    @Override
    public void saveNewPokemon(Pokemon pokemon) {
        pokemonDao.insert(pokemon.getName(), pokemon.getHitPoints(), pokemon.getCombatPower(), 1);
    }

    @Override
    public Pokemon getPokemon(int id) {
        PokemonDto pokemonDto = pokemonDao.fetch(id);
        Type pokemonType = typeService.getTypeById(pokemonDto.getPokemonTypeId());
        List<Integer> attackIds = pokemonAttackService.fetch(pokemonDto.getId());

        List<Attack> attacks = new ArrayList<>();
        for (Integer attackId : attackIds) {
            Attack attack = attackService.getAttackById(attackId); //this shouldn't go to DB
            attacks.add(attack);
        }

        Pokemon pokemon = new Pokemon();
        pokemon.setId(pokemonDto.getId());
        pokemon.setName(pokemonDto.getName());
        pokemon.setHitPoints(pokemonDto.getHitPoints());
        pokemon.setCombatPower(pokemonDto.getCombatPower());
        pokemon.setType(pokemonType);
        pokemon.setAttacks(attacks);

        return pokemon;
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
