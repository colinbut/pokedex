/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.api;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class PokemonRepresentation {

    private int id;
    private String name;
    private int hitPoints;
    private int combatPower;
    private TypeRepresentation type;
    private List<AttackRepresentation> attacks;

    public PokemonRepresentation() {

    }

    @JsonProperty
    public int getId() {
        return id;
    }

    @JsonProperty
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public int getHitPoints() {
        return hitPoints;
    }

    @JsonProperty
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    @JsonProperty
    public int getCombatPower() {
        return combatPower;
    }

    @JsonProperty
    public void setCombatPower(int combatPower) {
        this.combatPower = combatPower;
    }

    @JsonProperty
    public TypeRepresentation getType() {
        return type;
    }

    @JsonProperty
    public void setType(TypeRepresentation type) {
        this.type = type;
    }

    @JsonProperty
    public List<AttackRepresentation> getAttacks() {
        return attacks;
    }

    @JsonProperty
    public void setAttacks(List<AttackRepresentation> attacks) {
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
        PokemonRepresentation that = (PokemonRepresentation) o;
        return id == that.id &&
            hitPoints == that.hitPoints &&
            combatPower == that.combatPower &&
            Objects.equals(name, that.name) &&
            Objects.equals(type, that.type) &&
            Objects.equals(attacks, that.attacks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, hitPoints, combatPower, type, attacks);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PokemonRepresentation{");
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
