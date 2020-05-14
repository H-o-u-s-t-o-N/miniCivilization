package com.game.miniCivilization.domain;

import lombok.*;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;

@Entity
//@Getter
//@Setter
//@NoArgsConstructor
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Tile {
    @Id
//    @GeneratedValue()
    private int id;

    private int coordX;
    private int coordY;
    @OneToOne
    private City city;
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

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
