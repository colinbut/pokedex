/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.db.jdbi.dto;

import java.util.Objects;

public class PokemonAttackDto {

    private int pokemonId;
    private int attackId;

    public int getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public int getAttackId() {
        return attackId;
    }

    public void setAttackId(int attackId) {
        this.attackId = attackId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PokemonAttackDto that = (PokemonAttackDto) o;
        return pokemonId == that.pokemonId &&
            attackId == that.attackId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pokemonId, attackId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PokemonAttackDto{");
        sb.append("pokemonId=").append(pokemonId);
        sb.append(", attackId=").append(attackId);
        sb.append('}');
        return sb.toString();
    }

}
