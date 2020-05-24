package com.game.miniCivilization.domain.units;

import com.game.miniCivilization.domain.Player;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import javax.persistence.Entity;

@Entity
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ArcherVeteran extends Archer {

    public ArcherVeteran() {
    }

    public ArcherVeteran(Player player) {
        this.player = player;
        this.health = 100;
        this.damage = 200;
        this.experience = 0;
        this.radius = 2;
        this.mustMoveAfterBattle = false;
        this.actionPoint = 0;
        this.finalActionPoint = 2;
    }

    @Override
    public void reName(String tile, String player) {
        this.name = "ArcherVeteran " + tile + " " + player;
    }
}
