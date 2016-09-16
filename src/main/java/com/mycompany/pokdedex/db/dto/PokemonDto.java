/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.db.dto;

import java.util.Objects;

public class PokemonDto {

    private int id;
    private String name;
    private int hitPoints;
    private int combatPower;
    private int pokemonTypeId;

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

    public int getPokemonTypeId() {
        return pokemonTypeId;
    }

    public void setPokemonTypeId(int pokemonTypeId) {
        this.pokemonTypeId = pokemonTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PokemonDto that = (PokemonDto) o;
        return id == that.id &&
            hitPoints == that.hitPoints &&
            combatPower == that.combatPower &&
            pokemonTypeId == that.pokemonTypeId &&
            Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, hitPoints, combatPower, pokemonTypeId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PokemonDto{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", hitPoints=").append(hitPoints);
        sb.append(", combatPower=").append(combatPower);
        sb.append(", pokemonTypeId=").append(pokemonTypeId);
        sb.append('}');
        return sb.toString();
    }

}
