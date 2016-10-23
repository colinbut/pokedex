/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.db.hibernate.transformer;

import com.mycompany.pokedex.core.domain.Attack;
import com.mycompany.pokedex.db.hibernate.entity.AttackEntity;

public class AttackEntityAttackModelTransformer {

    public static AttackEntity getEntityFromModel(Attack attack) {
        AttackEntity attackEntity = new AttackEntity();
        attackEntity.setId(1);
        attackEntity.setAttackName(attack.getName());
        attackEntity.setPower(attack.getPower());
        attackEntity.setAccuracy(attack.getAccuracy());
        return attackEntity;
    }

    public static Attack getModelFromEntity(AttackEntity attackEntity) {
        Attack attack = new Attack();
        attack.setName(attackEntity.getAttackName());
        attack.setPower(attackEntity.getPower());
        attack.setAccuracy(attackEntity.getAccuracy());
        return attack;
    }
}
