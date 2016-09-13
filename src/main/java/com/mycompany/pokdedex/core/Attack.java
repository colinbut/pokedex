/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.core;

import java.util.Objects;

public class Attack {

    private int power;
    private int accuracy;
    private Type type;

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Attack attack = (Attack) o;
        return power == attack.power
            && accuracy == attack.accuracy
            && type == attack.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(power, accuracy, type);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Attack{");
        sb.append("power=").append(power);
        sb.append(", accuracy=").append(accuracy);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
