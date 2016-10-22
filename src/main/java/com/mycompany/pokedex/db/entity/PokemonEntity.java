/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.pokedex.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pokemon")
public class PokemonEntity {

    private int id;
    private String name;
    private int hitPoints;
    private int combatPower;
    private PokemonTypeEntity pokemonType;

    private Set<AttackEntity> attacks = new HashSet<>(0);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    @Column(name = "name", length = 25)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "hit_points")
    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    @Column(name = "combat_power")
    public int getCombatPower() {
        return combatPower;
    }

    public void setCombatPower(int combatPower) {
        this.combatPower = combatPower;
    }

    @ManyToOne
    public PokemonTypeEntity getPokemonType() {
        return pokemonType;
    }

    public void setPokemonType(PokemonTypeEntity pokemonType) {
        this.pokemonType = pokemonType;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pokemon_attack",
        joinColumns = { @JoinColumn(name = "pokemon_id", nullable = false, updatable = false) },
        inverseJoinColumns = { @JoinColumn(name = "attack_id", nullable = false, updatable = false) }
    )
    public Set<AttackEntity> getAttacks() {
        return attacks;
    }

    public void setAttacks(Set<AttackEntity> attacks) {
        this.attacks = attacks;
    }
}
