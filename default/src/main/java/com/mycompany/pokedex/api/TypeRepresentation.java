/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class TypeRepresentation {

    private String typeName;

    public TypeRepresentation() {

    }

    @JsonProperty
    public String getTypeName() {
        return typeName;
    }

    @JsonProperty
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TypeRepresentation that = (TypeRepresentation) o;
        return Objects.equals(typeName, that.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeName);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TypeRepresentation{");
        sb.append("typeName='").append(typeName).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
