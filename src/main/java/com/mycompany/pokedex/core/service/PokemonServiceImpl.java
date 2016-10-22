/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.core.service;

import com.mycompany.pokedex.core.domain.Attack;
import com.mycompany.pokedex.core.domain.Pokemon;
import com.mycompany.pokedex.core.domain.Type;
import com.mycompany.pokedex.db.jdbi.PokemonDaoJDBI;
import com.mycompany.pokedex.db.jdbi.dto.PokemonDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PokemonServiceImpl implements PokemonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonServiceImpl.class);

    private final PokemonDaoJDBI pokemonDaoJDBI;
    private final AttackService attackService;
    private final TypeService typeService;
    private final PokemonAttackService pokemonAttackService;

    public PokemonServiceImpl(PokemonDaoJDBI pokemonDaoJDBI,
                              AttackService attackService,
                              TypeService typeService,
                              PokemonAttackService pokemonAttackService) {
        this.pokemonDaoJDBI = pokemonDaoJDBI;
        this.attackService = attackService;
        this.typeService = typeService;
        this.pokemonAttackService = pokemonAttackService;
    }


    @Override
    public void saveNewPokemon(Pokemon pokemon) {
        LOGGER.info("Inserting new pokemon {} into system", pokemon);
        pokemonDaoJDBI.insert(pokemon.getName(), pokemon.getHitPoints(), pokemon.getCombatPower(), 1);
        LOGGER.info("Inserted new pokemon {} into the system", pokemon);
    }

    @Override
    public Pokemon getPokemon(int id) {
        LOGGER.info("Fetching pokemon with id: {}", id);
        PokemonDto pokemonDto = pokemonDaoJDBI.fetch(id);
        Pokemon pokemon = new PokemonDtoTransformer().transformDtoToDomain(pokemonDto);
        LOGGER.debug("Fetched pokemon {} with id: {} from the system", pokemon, id);
        return pokemon;
    }

    @Override
    public void updatePokemon(Pokemon pokemon) {
        LOGGER.info("Updating pokemon {}", pokemon);
        throw new UnsupportedOperationException("Not Yet Implemented!");
        //LOGGER.info("Updated pokemon {}", pokemon);
    }

    @Override
    public void deletePokemon(int id) {
        LOGGER.info("Deleting pokemon with id: {} from the system", id);
        pokemonDaoJDBI.delete(id);
        LOGGER.info("Deleted pokemon with id: {}", id);
    }



    private class PokemonDtoTransformer {

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
            return typeService.getTypeById(typeId);
        }

        private List<Attack> transformAttacks(PokemonDto pokemonDto) {
            List<Integer> attackIds = pokemonAttackService.fetch(pokemonDto.getId());
            List<Attack> attacks = new ArrayList<>();
            for (Integer attackId : attackIds) {
                LOGGER.debug("Getting list of attacks for pokemon with id: {}", pokemonDto.getId());
                Attack attack = attackService.getAttackById(attackId);
                attacks.add(attack);
            }
            LOGGER.debug("Got pokemon attacks {} for pokemon with id: {}", attacks, pokemonDto.getId());
            return attacks;
        }
    }

}
