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
//public class Archer extends Unit {
//    @Id
//    private Integer id;
//    public Archer() {
//        this.setCost(300);
//        this.setDamage(100);
//        this.setHealth(100);
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    @Override
//    public void setName(String name) {
//        super.setName("Archer "+name);
//    }
//}
