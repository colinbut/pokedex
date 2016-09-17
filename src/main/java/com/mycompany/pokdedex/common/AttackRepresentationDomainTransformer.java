/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.common;

import com.mycompany.pokdedex.api.AttackRepresentation;
import com.mycompany.pokdedex.api.TypeRepresentation;
import com.mycompany.pokdedex.core.domain.Attack;
import com.mycompany.pokdedex.core.domain.Type;

import java.util.ArrayList;
import java.util.List;

public final class AttackRepresentationDomainTransformer {

    private AttackRepresentationDomainTransformer() {}

    public static Attack asDomain(AttackRepresentation attackRepresentation) {
        Attack attack = new Attack();
        attack.setName(attackRepresentation.getName());
        attack.setPower(attackRepresentation.getPower());
        attack.setAccuracy(attackRepresentation.getPower());
        attack.setType(Type.valueOf(attackRepresentation.getTypeRepresentation().getTypeName()));
        return attack;
    }

    public static List<Attack> asDomainList(List<AttackRepresentation> attackRepresentationList){
        List<Attack> attacks = new ArrayList<>();
        for (AttackRepresentation attackRepresentation : attackRepresentationList) {
            attacks.add(asDomain(attackRepresentation));
        }
        return attacks;
    }

    public static AttackRepresentation asRepresentation(Attack attack) {
        AttackRepresentation attackRepresentation = new AttackRepresentation();
        attackRepresentation.setName(attack.getName());
        attackRepresentation.setPower(attack.getPower());
        attackRepresentation.setAccuracy(attack.getAccuracy());
        TypeRepresentation typeRepresentation = new TypeRepresentation();
        typeRepresentation.setTypeName(attack.getType().toString());
        attackRepresentation.setTypeRepresentation(typeRepresentation);
        return attackRepresentation;
    }

    public static List<AttackRepresentation> asRepresentationList(List<Attack> attacks) {
        List<AttackRepresentation> attackRepresentationList = new ArrayList<>();
        for (Attack attack : attacks) {
            attackRepresentationList.add(asRepresentation(attack));
        }
        return attackRepresentationList;
    }

}
