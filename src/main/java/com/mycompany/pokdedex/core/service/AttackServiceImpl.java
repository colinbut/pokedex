/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.core.service;

import com.mycompany.pokdedex.core.domain.Attack;
import com.mycompany.pokdedex.db.AttackDao;
import com.mycompany.pokdedex.db.dto.AttackDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttackServiceImpl implements AttackService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttackServiceImpl.class);

    private final AttackDao attackDao;

    private Map<Integer, AttackDto> attackMap = new HashMap<>();

    public AttackServiceImpl(AttackDao attackDao) {
        this.attackDao = attackDao;

        LOGGER.debug("Loading data from the database into memory");

        List<AttackDto> attackDtoList = attackDao.fetchList();
        for (AttackDto attackDto : attackDtoList) {
            LOGGER.trace("Inserted {} into memory map", attackDto);
            attackMap.put(attackDto.getId(), attackDto);
        }
        LOGGER.debug("Finished loading data from the database into memory");
    }


    @Override
    public Attack getAttackById(int id) {
        AttackDto attackDto = attackMap.get(id);
        Attack attack = new Attack();
        attack.setName(attackDto.getAttackName());
        attack.setPower(attackDto.getPower());
        attack.setAccuracy(attackDto.getAccuracy());
        return attack;
    }
}
