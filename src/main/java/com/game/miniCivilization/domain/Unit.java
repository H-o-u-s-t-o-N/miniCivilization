package com.game.miniCivilization.domain;

import lombok.Data;
import lombok.Generated;
import org.springframework.context.annotation.Scope;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Scope("prototype")
@Embeddable
public class Unit {
    @Id
    @Generated
    private long id;
    private int cost;
    private int health;
    private int damage;
    private String name;

}
