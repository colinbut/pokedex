/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.db.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "attack")
public class AttackEntity {

    private int id;
    private String attackName;
    private int power;
    private int accuracy;
    private PokemonTypeEntity type;

    private Set<PokemonEntity> pokemons = new HashSet<>(0);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    @Column(name = "name")
    public String getAttackName() {
        return attackName;
    }

    public void setAttackName(String attackName) {
        this.attackName = attackName;
    }

    @Column(name = "power")
    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Column(name = "accuracy")
    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    @ManyToOne
    public PokemonTypeEntity getType() {
        return type;
    }

    public void setType(PokemonTypeEntity type) {
        this.type = type;
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "attacks")
    public Set<PokemonEntity> getPokemons() {
        return pokemons;
    }

    public void setPokemons(Set<PokemonEntity> pokemons) {
        this.pokemons = pokemons;
    }
}
