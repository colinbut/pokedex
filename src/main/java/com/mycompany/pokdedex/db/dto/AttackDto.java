/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokdedex.db.dto;

import java.util.Objects;

public class AttackDto {

    private int id;
    private String attackName;
    private int power;
    private int accuracy;
    private int type;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAttackName() {
        return attackName;
    }

    public void setAttackName(String attackName) {
        this.attackName = attackName;
    }

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
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
        AttackDto attackDto = (AttackDto) o;
        return id == attackDto.id &&
            power == attackDto.power &&
            accuracy == attackDto.accuracy &&
            type == attackDto.type &&
            Objects.equals(attackName, attackDto.attackName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, attackName, power, accuracy, type);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AttackDto{");
        sb.append("id=").append(id);
        sb.append(", attackName='").append(attackName).append('\'');
        sb.append(", power=").append(power);
        sb.append(", accuracy=").append(accuracy);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
