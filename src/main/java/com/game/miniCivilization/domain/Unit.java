package com.game.miniCivilization.domain;


import javax.persistence.*;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    protected int cost;
    protected int health;
    protected int damage;
    protected String name;
    protected int coordX;
    protected int coordY;
    protected int experience;
    protected int radius;
    protected boolean mustMoveAfterBattle;
    protected int actionPoint;
    protected int finalActionPoint;
    @ManyToOne(fetch = FetchType.EAGER)
    protected Player player;

    public Unit() {
    }
    public abstract void reName(String tile, String player);

    public void setCoordinats(Tile tile){
        coordX = tile.getCoordX();
        coordY = tile.getCoordY();
    };

    public Long getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public Player getPlayer() {
        return player;
    }

    public int getExperience() {
        return experience;
    }

    public int getRadius() {
        return radius;
    }

    public boolean isMustMoveAfterBattle() {
        return mustMoveAfterBattle;
    }

    public int getActionPoint() {
        return actionPoint;
    }

    public int getFinalActionPoint() {
        return finalActionPoint;
    }


    //    =======================================================


    public void setHealth(int health) {
        this.health = health;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setActionPoint(int actionPoint) {
        this.actionPoint = actionPoint;
    }
}
