package com.game.miniCivilization.domain.units;


import com.game.miniCivilization.domain.Player;
import com.game.miniCivilization.domain.Unit;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import javax.persistence.Entity;

@Entity
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Colonist extends Unit {

    public Colonist() {
    }

    public Colonist(Player player) {
        this.player = player;
        this.health = 50;
        this.damage = 0;
        this.experience = 0;
        this.radius = 0;
        this.mustMoveAfterBattle = false;
        this.actionPoint = 0;
        this.finalActionPoint = 3;
    }

    @Override
    public void reName(String tile, String player) {

    }
}
