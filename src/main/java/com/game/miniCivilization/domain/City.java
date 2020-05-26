package com.game.miniCivilization.domain;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;


@Entity
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    protected Player player;

    public City() {
    }

    public City(Player player) {
        this.player = player;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Player getPlayer() {
        return player;
    }

//    ====================================================================


    public void setName(String name) {
        this.name = "City " + name;
    }
}
