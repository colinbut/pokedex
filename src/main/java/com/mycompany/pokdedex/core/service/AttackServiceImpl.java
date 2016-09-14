/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.core.service;

import com.mycompany.pokdedex.core.domain.Attack;
import com.mycompany.pokdedex.db.AttackDao;
import com.mycompany.pokdedex.db.dto.AttackDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttackServiceImpl implements AttackService {

    private final AttackDao attackDao;

    private Map<Integer, String> attackMap = new HashMap<>();

    public AttackServiceImpl(AttackDao attackDao) {
        this.attackDao = attackDao;

        List<AttackDto> attackDtoList = attackDao.fetchList();
        for (AttackDto attackDto : attackDtoList) {
            attackMap.put(attackDto.getId(), attackDto.getAttackName());
        }

    }


    @Override
    public Attack getAttackById(int id) {
        return null;
    }
}
