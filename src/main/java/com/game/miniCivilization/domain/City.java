package com.game.miniCivilization.domain;

import lombok.Data;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import javax.annotation.processing.Generated;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
@Data
@Embeddable
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class City {
    @Id
    @GeneratedValue
    private long id;
}
