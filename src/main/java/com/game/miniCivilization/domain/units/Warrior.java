//package com.game.miniCivilization.domain.units;
//
//import com.game.miniCivilization.domain.Unit;
//import lombok.Data;
//import org.springframework.beans.factory.config.BeanDefinition;
//import org.springframework.context.annotation.Scope;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//
//@Entity
//@Data
//@Scope(BeanDefinition.SCOPE_PROTOTYPE)
//public class Warrior extends Unit {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long id;
//
//
//    public Warrior() {
//        this.damage = 50;
//        this.health = 200;
//    }
//
//
//    @Override
//    public void setName(String name) {
//        super.setName("Warrior_"+name);
//    }
//}
