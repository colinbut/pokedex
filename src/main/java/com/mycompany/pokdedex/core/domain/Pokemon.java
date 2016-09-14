/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.core.domain;

import java.util.List;
import java.util.Objects;

public class Pokemon {

    private int id;
    private String name;
    private int hitPoints;
    private int combatPower;
    private Type type;
    private List<Attack> attacks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getCombatPower() {
        return combatPower;
    }

    public void setCombatPower(int combatPower) {
        this.combatPower = combatPower;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(List<Attack> attacks) {
        this.attacks = attacks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pokemon pokemon = (Pokemon) o;
        return id == pokemon.id
            && hitPoints == pokemon.hitPoints
            && combatPower == pokemon.combatPower
            && Objects.equals(name, pokemon.name)
            && type == pokemon.type
            && Objects.equals(attacks, pokemon.attacks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, hitPoints, combatPower, type, attacks);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pokemon{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", hitPoints=").append(hitPoints);
        sb.append(", combatPower=").append(combatPower);
        sb.append(", type=").append(type);
        sb.append(", attacks=").append(attacks);
        sb.append('}');
        return sb.toString();
    }

}
