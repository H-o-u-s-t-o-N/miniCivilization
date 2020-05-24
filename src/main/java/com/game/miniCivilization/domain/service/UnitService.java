package com.game.miniCivilization.domain.service;

import com.game.miniCivilization.domain.Tile;
import com.game.miniCivilization.domain.Unit;
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


    public void moveUnit(Long tileIdStart, Long tileIdEnd){

        Tile tileStart = tileRepo.findById(tileIdStart).get();
        Tile tileEnd = tileRepo.findById(tileIdEnd).get();
        Unit tempUnitA = tileStart.getUnit();
        Unit tempUnitB = tileEnd.getUnit();

        if(tempUnitA != null) {
            if (canMove(tempUnitA, tileEnd)) {
                if (tempUnitB != null) {
                    if (canFight(tempUnitA, tempUnitB)) {
                        if(canKill(tempUnitA,tempUnitB)){
                            if(tempUnitA.isMustMoveAfterBattle()) {
                                tileEnd.setUnit(tempUnitA);
                                tempUnitA.setCoordinats(tileEnd);
                                tileStart.setUnit(null);

                                setActionPointAfterMove(tempUnitA,tileEnd);

                                unitRepo.delete(tempUnitB);
                                unitRepo.save(tempUnitA);
                                tileRepo.saveAll(asList(tileEnd,tileStart));
                            }else {
                                tempUnitA.setActionPoint(tempUnitA.getActionPoint() - 1);
                                tileEnd.setUnit(null);

                                unitRepo.delete(tempUnitB);
                                unitRepo.save(tempUnitA);
                                tileRepo.save(tileEnd);
                            }
                        }else {
                            tempUnitB.setHealth(tempUnitB.getHealth() - tempUnitA.getDamage());

                            tempUnitA.setActionPoint(tempUnitA.getActionPoint()-1);

                            unitRepo.saveAll(asList(tempUnitA, tempUnitB));
                        }
                    }else{
                        Tile tempTarget = getTargetTile(tempUnitA, tileEnd);

                        setActionPointAfterMove(tempUnitA,tempTarget);

                        tempTarget.setUnit(tempUnitA);
                        tempUnitA.setCoordinats(tempTarget);
                        if(tempTarget != tileEnd){
                            tileStart.setUnit(null);
                        }
                        unitRepo.save(tempUnitA);
                        tileRepo.saveAll(asList(tileStart,tempTarget));
                    }
                }else {
                    tileEnd.setUnit(tempUnitA);
                    tileStart.setUnit(null);

                    setActionPointAfterMove(tempUnitA,tileEnd);

                    tileRepo.saveAll(asList(tileEnd,tileStart));
                }
            }if(canFight(tempUnitA, tempUnitB)){
                if(canKill(tempUnitA, tempUnitB)){
                    tileEnd.setUnit(null);

                    tempUnitA.setActionPoint(tempUnitA.getActionPoint()-1);

                    unitRepo.delete(tempUnitB);
                    unitRepo.save(tempUnitA);
                }else {
                    tempUnitB.setHealth(tempUnitB.getHealth() - tempUnitA.getDamage());

                    tempUnitA.setActionPoint(tempUnitA.getActionPoint()-1);

                    unitRepo.saveAll(asList(tempUnitA, tempUnitB));
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

    private void setActionPointAfterMove(Unit unit, Tile endpoint){
        int x = Math.abs(unit.getCoordX()-endpoint.getCoordX());
        int y = Math.abs(unit.getCoordY()-endpoint.getCoordY());
        int z = Math.max(x,y);
        unit.setActionPoint(unit.getActionPoint() - z);
    }
}
