/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class AttackRepresentation {

    private String name;
    private int power;
    private int accuracy;
    private TypeRepresentation typeRepresentation;

    public AttackRepresentation() {

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
    public TypeRepresentation getTypeRepresentation() {
        return typeRepresentation;
    }

    @JsonProperty
    public void setTypeRepresentation(TypeRepresentation typeRepresentation) {
        this.typeRepresentation = typeRepresentation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AttackRepresentation that = (AttackRepresentation) o;
        return power == that.power &&
            accuracy == that.accuracy &&
            Objects.equals(name, that.name) &&
            Objects.equals(typeRepresentation, that.typeRepresentation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, power, accuracy, typeRepresentation);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AttackRepresentation{");
        sb.append("name='").append(name).append('\'');
        sb.append(", power=").append(power);
        sb.append(", accuracy=").append(accuracy);
        sb.append(", typeRepresentation=").append(typeRepresentation);
        sb.append('}');
        return sb.toString();
    }
}
