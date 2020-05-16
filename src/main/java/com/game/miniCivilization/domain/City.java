package com.game.miniCivilization.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Data
@NoArgsConstructor
public class City {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = "City_" + name;
    }
}
