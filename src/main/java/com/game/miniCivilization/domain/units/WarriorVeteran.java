package com.game.miniCivilization.domain.units;

import com.game.miniCivilization.domain.Player;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import javax.persistence.Entity;

@Entity
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class WarriorVeteran extends Warrior {

    public WarriorVeteran() {
    }

    public WarriorVeteran(Player player) {
        this.player = player;
        this.health = 200;
        this.damage = 100;
        this.experience = 0;
        this.radius = 1;
        this.mustMoveAfterBattle = true;
        this.actionPoint = 0;
        this.finalActionPoint = 2;
    }

    @Override
    public void reName(String tile, String player) {
        this.name = "WarriorVeteran " + tile + " " + player;
    }
}
