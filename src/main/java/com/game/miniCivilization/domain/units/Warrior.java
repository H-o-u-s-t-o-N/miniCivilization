//package com.game.miniCivilization.domain.units;
//
//import com.game.miniCivilization.domain.Unit;
//import lombok.Data;
//import org.springframework.beans.factory.config.BeanDefinition;
//import org.springframework.context.annotation.Scope;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//
//@Entity
//@Data
//@Scope(BeanDefinition.SCOPE_PROTOTYPE)
//public class Warrior extends Unit {
//
//    public Warrior() {
//        this.setCost(300);
//        this.setDamage(50);
//        this.setHealth(200);
//    }
//    @Id
//    @Override
//    public void setId(long id) {
//        super.setId(id);
//    }
//
//    @Override
//    public void setName(String name) {
//        super.setName("Warrior "+name);
//    }
//}
