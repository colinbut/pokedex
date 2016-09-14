/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PokemonType {

    private String typeName;

    public PokemonType() {

    }

    @JsonProperty
    public String getTypeName() {
        return typeName;
    }

    @JsonProperty
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}
