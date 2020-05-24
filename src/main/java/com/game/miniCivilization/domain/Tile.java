package com.game.miniCivilization.domain;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Tile {
    @Id
    private Long id;
    private int coordX;
    private int coordY;
    @OneToOne(fetch = FetchType.EAGER)
    private City city;
    @OneToOne(fetch = FetchType.EAGER)
    public Unit unit;

    private String land;
    private String name;

    public Tile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void setLand(String land) {
        this.land = land;
    }

    public String getLand() {
        return land;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
