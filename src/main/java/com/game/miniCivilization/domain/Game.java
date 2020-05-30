package com.game.miniCivilization.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Game {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Player playerA;
    @OneToOne
    private Player playerB;

    private Long moneyA;
    private Long moneyB;

    private String name;

    public Game() {
    }

    public Game(Player playerA) {
        this.playerA = playerA;
    }

    public Long getId() {
        return id;
    }

    public Player getPlayerA() {
        return playerA;
    }

    public Player getPlayerB() {
        return playerB;
    }

    public String getName() {
        return name;
    }

    public Long getMoneyA() {
        return moneyA;
    }

    public Long getMoneyB() {
        return moneyB;
    }

    //    ==========================================

    public void setPlayerB(Player playerB) {
        this.playerB = playerB;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartMoney() {
        this.moneyA = 1000L;
        this.moneyB = 1000L;
    }

    public void setMoneyA(Long moneyA) {
        this.moneyA = moneyA;
    }

    public void setMoneyB(Long moneyB) {
        this.moneyB = moneyB;
    }
}
