/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.core;

import com.mycompany.pokedex.db.jdbi.AttackDaoJDBI;
import com.mycompany.pokedex.db.jdbi.dto.AttackDto;
import com.mycompany.pokedex.resources.PokemonApiResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttackInternalMap {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonApiResource.class);

    private final AttackDaoJDBI attackDaoJDBI;

    private Map<Integer, AttackDto> attackMap = new HashMap<>();

    public AttackInternalMap(AttackDaoJDBI attackDaoJDBI) {
        this.attackDaoJDBI = attackDaoJDBI;
        initializeAttackMap();
    }

    private void initializeAttackMap() {
        List<AttackDto> attackDtoList = attackDaoJDBI.fetchList();
        for (AttackDto attackDto : attackDtoList) {
            LOGGER.trace("Inserted {} into memory map", attackDto);
            attackMap.put(attackDto.getId(), attackDto);
        }
        LOGGER.debug("Finished loading attack data from the database into memory");
    }

    public AttackDto getAttackDto(int id) {
        return attackMap.get(id);
    }

}
