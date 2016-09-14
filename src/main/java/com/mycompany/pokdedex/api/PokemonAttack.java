/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PokemonAttack {

    private String name;
    private int power;
    private int accuracy;
    private PokemonType pokemonType;

    public PokemonAttack() {

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
    public int getPower() {
        return power;
    }

    @JsonProperty
    public void setPower(int power) {
        this.power = power;
    }

    @JsonProperty
    public int getAccuracy() {
        return accuracy;
    }

    @JsonProperty
    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    @JsonProperty
    public PokemonType getPokemonType() {
        return pokemonType;
    }

    @JsonProperty
    public void setPokemonType(PokemonType pokemonType) {
        this.pokemonType = pokemonType;
    }
}
