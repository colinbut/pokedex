/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.db.jdbi.transformer;

import com.mycompany.pokedex.core.AttackInternalMap;
import com.mycompany.pokedex.core.PokemonTypeInternalMap;
import com.mycompany.pokedex.core.domain.Attack;
import com.mycompany.pokedex.core.domain.Pokemon;
import com.mycompany.pokedex.core.domain.Type;
import com.mycompany.pokedex.db.jdbi.PokemonAttackDaoJDBI;
import com.mycompany.pokedex.db.jdbi.dto.AttackDto;
import com.mycompany.pokedex.db.jdbi.dto.PokemonAttackDto;
import com.mycompany.pokedex.db.jdbi.dto.PokemonDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PokemonDtoTransformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonDtoTransformer.class);

    private final PokemonAttackDaoJDBI pokemonAttackDaoJDBI;
    private final PokemonTypeInternalMap pokemonTypeInternalMap;
    private final AttackInternalMap attackInternalMap;

    public PokemonDtoTransformer(PokemonAttackDaoJDBI pokemonAttackDaoJDBI,
                                 PokemonTypeInternalMap pokemonTypeInternalMap,
                                 AttackInternalMap attackInternalMap) {
        this.pokemonAttackDaoJDBI = pokemonAttackDaoJDBI;
        this.pokemonTypeInternalMap = pokemonTypeInternalMap;
        this.attackInternalMap = attackInternalMap;
    }

    public Pokemon transformDtoToDomain(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(pokemonDto.getId());
        pokemon.setName(pokemonDto.getName());
        pokemon.setHitPoints(pokemonDto.getHitPoints());
        pokemon.setCombatPower(pokemonDto.getCombatPower());
        pokemon.setType(transformType(pokemonDto.getPokemonTypeId()));
        pokemon.setAttacks(transformAttacks(pokemonDto));
        return pokemon;
    }

    private Type transformType(int typeId) {
        String pokemonType = pokemonTypeInternalMap.getPokemonType(typeId);
        LOGGER.debug("Trying to get pokemon type for id: {}, {}", typeId, pokemonType);
        return Type.valueOf(pokemonType);
    }

    private List<Attack> transformAttacks(PokemonDto pokemonDto) {
        List<Integer> attackIds = fetchListOfPokemonAttacks(pokemonDto.getId());
        List<Attack> attacks = new ArrayList<>();
        for (Integer attackId : attackIds) {
            LOGGER.debug("Getting list of attacks for pokemon with id: {}", pokemonDto.getId());
            AttackDto attackDto = attackInternalMap.getAttackDto(attackId);
            Attack attack = tranformAttackDtoToAttackModel(attackDto);
            attacks.add(attack);
        }
        LOGGER.debug("Got pokemon attacks {} for pokemon with id: {}", attacks, pokemonDto.getId());
        return attacks;
    }


    private List<Integer> fetchListOfPokemonAttacks(int pokemonId) {
        LOGGER.info("Fetching list of pokemon attacks");
        List<Integer> attackIds = new ArrayList<>();
        List<PokemonAttackDto> pokemonAttackDtos = pokemonAttackDaoJDBI.fetch(pokemonId);
        for (PokemonAttackDto pokemonAttackDto : pokemonAttackDtos) {
            attackIds.add(pokemonAttackDto.getAttackId());
        }
        LOGGER.debug("Fetched list of pokemon attack ids: {}", attackIds);
        return attackIds;
    }

    private Attack tranformAttackDtoToAttackModel(AttackDto attackDto) {
        Attack attack = new Attack();
        attack.setName(attackDto.getAttackName());
        attack.setPower(attackDto.getPower());
        attack.setAccuracy(attackDto.getAccuracy());
        attack.setType(transformType(attackDto.getType()));
        return attack;
    }
}
