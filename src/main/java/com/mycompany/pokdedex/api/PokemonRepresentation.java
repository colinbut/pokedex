/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.api;


import com.fasterxml.jackson.annotation.JsonProperty;

public class PokemonRepresentation {

    private int id;
    private String name;
    private int hitPoints;
    private int combatPower;
    private TypeRepresentation type;

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

}
