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
//public class Archer extends Unit {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long id;
//
//    public Archer() {
//        this.damage = 100;
//        this.health = 100;
//    }
//
//    @Override
//    public void setName(String name) {
//        super.setName("Archer_"+name);
//    }
//}
