package com.game.miniCivilization.domain;

import lombok.*;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
//@Getter
//@Setter
//@NoArgsConstructor
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Tile {
    @Id
//    @GeneratedValue
    private int id;
//    @Embedded
//    private Unit unit;
    private String name;

    public Tile() {
    }

    public Tile(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
