/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.db.hibernate.transformer;

import com.mycompany.pokedex.core.domain.Attack;
import com.mycompany.pokedex.db.hibernate.entity.AttackEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AttackEntityAttackModelTransformer {

    public static AttackEntity getEntityFromModel(Attack attack) {
        AttackEntity attackEntity = new AttackEntity();
        attackEntity.setAttackName(attack.getName());
        attackEntity.setPower(attack.getPower());
        attackEntity.setAccuracy(attack.getAccuracy());
        attackEntity.setType(PokemonTypeEntityPokemonTypeTransformer.getEntityFromModel(attack.getType()));
        return attackEntity;
    }

    public static Attack getModelFromEntity(AttackEntity attackEntity) {
        Attack attack = new Attack();
        attack.setName(attackEntity.getAttackName());
        attack.setPower(attackEntity.getPower());
        attack.setAccuracy(attackEntity.getAccuracy());
        attack.setType(PokemonTypeEntityPokemonTypeTransformer.getModelFromEntity(attackEntity.getType()));
        return attack;
    }

    public static List<Attack> getModelListFromEntitySet(Set<AttackEntity> attackEntities) {
        List<Attack> attacks = new ArrayList<>();
        for (AttackEntity attackEntity : attackEntities) {
            attacks.add(getModelFromEntity(attackEntity));
        }
        return attacks;
    }

    public static Set<AttackEntity> getEntitySetFromModelList(List<Attack> attackList) {
        Set<AttackEntity> attackEntities = new HashSet<>();
        for (Attack attack : attackList) {
            attackEntities.add(getEntityFromModel(attack));
        }
        return attackEntities;
    }
}
