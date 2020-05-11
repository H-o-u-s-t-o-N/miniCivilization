package com.game.miniCivilization.domain;

import lombok.Data;
import org.springframework.context.annotation.Scope;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Scope("prototype")
public class Tile {
    @Id
    @GeneratedValue
    private long id;
    @Embedded
    private Unit unit;
}
