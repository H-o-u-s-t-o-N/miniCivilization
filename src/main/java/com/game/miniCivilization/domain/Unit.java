package com.game.miniCivilization.domain;

import lombok.Data;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int cost;
    private int health;
    private int damage;
    private String name;

    public Unit() {
        this.cost = 300;
        this.health = 100;
        this.damage = 50;
        this.name = "Unknown";
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = "Unit_"+name;
    }
}
