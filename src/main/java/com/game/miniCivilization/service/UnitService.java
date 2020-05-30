package com.game.miniCivilization.service;

import com.game.miniCivilization.domain.Player;
import com.game.miniCivilization.domain.Tile;
import com.game.miniCivilization.domain.Unit;
import com.game.miniCivilization.domain.units.Archer;
import com.game.miniCivilization.domain.units.ArcherVeteran;
import com.game.miniCivilization.domain.units.Warrior;
import com.game.miniCivilization.domain.units.WarriorVeteran;
import com.game.miniCivilization.repository.TileRepo;
import com.game.miniCivilization.repository.UnitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Arrays.asList;

@Service
public class UnitService {
    @Autowired
    private UnitRepo unitRepo;
    @Autowired
    private TileRepo tileRepo;



    public void moveUnit(Long tileIdStart, Long tileIdEnd, Player player){

        Tile tileStart = tileRepo.findById(tileIdStart).get();
        Tile tileEnd = tileRepo.findById(tileIdEnd).get();
        Unit tempUnitA = tileStart.getUnit();
        Unit tempUnitB = tileEnd.getUnit();
        if(tempUnitA != null) {

            if (checkPlayer(tempUnitA,player)) {
//                ActionPoint to move
                if (canMove(tempUnitA, tileEnd)) {

                    if (tempUnitB != null) {
//                        check Enemy unit
                        if( ! tempUnitB.getPlayer().getUsername().equals(player.getUsername())) {
//                            use radius for check attack
                            if (canFight(tempUnitA, tempUnitB)) {
                                if (canKill(tempUnitA, tempUnitB)) {
                                    if (tempUnitA.isMustMoveAfterBattle()) {
                                        tileEnd.setUnit(tempUnitA);
                                        tileStart.setUnit(null);

                                        setActionPointAfterMove(tempUnitA, tileEnd);
                                        addEX(tempUnitA);
                                        tempUnitA.setCoordinats(tileEnd);

                                        unitRepo.save(tempUnitA);
                                        unitRepo.delete(tempUnitB);
                                        tileRepo.saveAll(asList(tileEnd, tileStart));

                                        if (checkExperience(tempUnitA)) {
                                            levelUp(tileEnd);
                                        }
                                    } else {
//                                    only for archers after battle
                                        tileEnd.setUnit(null);

                                        setActionPointAfterAttack(tempUnitA);
                                        addEX(tempUnitA);

                                        unitRepo.delete(tempUnitB);
                                        tileRepo.save(tileEnd);
                                    }
                                } else {
                                    tempUnitB.setHealth(tempUnitB.getHealth() - tempUnitA.getDamage());

                                    setActionPointAfterAttack(tempUnitA);

                                    unitRepo.save(tempUnitB);
                                }
                            } else {
//                            warrior can move but dont see enemy
                                Tile tempTarget = getTargetTile(tempUnitA, tileEnd);

                                setActionPointAfterMove(tempUnitA, tempTarget);

                                tempTarget.setUnit(tempUnitA);
                                tempUnitA.setCoordinats(tempTarget);
                                if (tempTarget != tileEnd) {
                                    tileStart.setUnit(null);
                                }
                                unitRepo.save(tempUnitA);
                                tileRepo.saveAll(asList(tileStart, tempTarget));
                            }
                        }
                    } else {
//                        only move
                        tileEnd.setUnit(tempUnitA);
                        tileStart.setUnit(null);

                        tempUnitA.setCoordinats(tileEnd);

                        setActionPointAfterMove(tempUnitA, tileEnd);

                        unitRepo.save(tempUnitA);
                        tileRepo.saveAll(asList(tileEnd, tileStart));
                    }
                }
//               attack archer if it cant move
                if (tempUnitB != null) {
//                    check Enemy unit
                    if (!tempUnitB.getPlayer().getUsername().equals(player.getUsername())) {
//                        use radius for check attack
                        if (canFight(tempUnitA, tempUnitB)) {
                            if (canKill(tempUnitA, tempUnitB)) {
                                tileEnd.setUnit(null);

                                setActionPointAfterAttack(tempUnitA);
                                addEX(tempUnitA);

                                unitRepo.delete(tempUnitB);

                                if (checkExperience(tempUnitA)) {
                                    levelUp(tileStart);
                                }
                            } else {
                                tempUnitB.setHealth(tempUnitB.getHealth() - tempUnitA.getDamage());

                                setActionPointAfterAttack(tempUnitA);

                                unitRepo.saveAll(asList(tempUnitA, tempUnitB));
                            }
                        }
                    }
                }
            }
        }
    }

    private Tile getTargetTile(Unit unit, Tile endpoint){
        int differX = endpoint.getCoordX() - unit.getCoordX();
        int differY = endpoint.getCoordY() - unit.getCoordY();

        int targetX;
        if(differX == 0) {
            targetX = endpoint.getCoordX();
        }else if(differX > 0) {
            targetX = endpoint.getCoordX() - 1;
        } else {
            targetX = endpoint.getCoordX() + 1;
        }

        int targetY ;
        if(differY == 0) {
            targetY = endpoint.getCoordY();
        }else if(differY > 0) {
            targetY = endpoint.getCoordY() - 1 ;
        } else {
            targetY = endpoint.getCoordY() + 1;
        }

        return tileRepo.findByCoordinats(targetX,targetY);
    }

    private boolean canKill(Unit unitA, Unit unitB){
        return unitB.getHealth()-unitA.getDamage() <= 0 ;
    }

    private boolean canFight(Unit unitA, Unit unitB){
        int differX = Math.abs(unitB.getCoordX()-unitA.getCoordX());
        int differY = Math.abs(unitB.getCoordY()-unitA.getCoordY());
        return (unitA.getRadius() >= differX && unitA.getRadius() >= differY);
    }

    private boolean canMove(Unit unit, Tile endpoint){
        int differX = Math.abs(endpoint.getCoordX()-unit.getCoordX());
        int differY = Math.abs(endpoint.getCoordY()-unit.getCoordY());
        return (unit.getActionPoint() >= differX && unit.getActionPoint() >= differY);
    }

    private boolean checkExperience(Unit unit){
        return unit.getExperience() >= 3;
    }

    private boolean checkPlayer(Unit unit, Player player){
        return unit.getPlayer().getUsername().equals(player.getUsername());
    }

    private void addEX(Unit unit){
        unit.setExperience(unit.getExperience() + 1);
        unitRepo.save(unit);
    }

    private void setActionPointAfterAttack(Unit unit){
        unit.setActionPoint(unit.getActionPoint() - 1);
        unitRepo.save(unit);
    }

    private void setActionPointAfterMove(Unit unit, Tile endpoint){
        int x = Math.abs(unit.getCoordX()-endpoint.getCoordX());
        int y = Math.abs(unit.getCoordY()-endpoint.getCoordY());
        int z = Math.max(x,y);
        unit.setActionPoint(unit.getActionPoint() - z);
        unitRepo.save(unit);
    }
    
    private void levelUp(Tile tile){
        Unit tempUnit = tile.getUnit();
        if(tempUnit.getClass() == Archer.class){
            ArcherVeteran veteran = new ArcherVeteran(tempUnit.getPlayer());
            veteran.setCoordinats(tile);
            veteran.reName(tempUnit.getPlayer().getUsername());
            tile.setUnit(veteran);
            unitRepo.save(veteran);
            unitRepo.delete(tempUnit);
            tileRepo.save(tile);
        }
        if(tempUnit.getClass() == Warrior.class){
            WarriorVeteran veteran = new WarriorVeteran(tempUnit.getPlayer());
            veteran.setCoordinats(tile);
            veteran.reName(tempUnit.getPlayer().getUsername());
            tile.setUnit(veteran);
            unitRepo.save(veteran);
            unitRepo.delete(tempUnit);
            tileRepo.save(tile);
        }
    }
}
