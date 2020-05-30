package com.game.miniCivilization.domain.units;

import com.game.miniCivilization.domain.Player;
import com.game.miniCivilization.domain.Unit;
import com.game.miniCivilization.domain.enums.Type;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import javax.persistence.Entity;


@Entity
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Archer extends Unit {

    public Archer() {
    }

    public Archer(Player player) {
        this.player = player;
        this.health = 100;
        this.damage = 100;
        this.experience = 0;
        this.radius = 2;
        this.mustMoveAfterBattle = false;
        this.actionPoint = 0;
        this.finalActionPoint = 2;
        this.type = Type.Archer;
    }

    @Override
    public void reName(String player) {
        this.name = "Archer " + player;
    }


}
