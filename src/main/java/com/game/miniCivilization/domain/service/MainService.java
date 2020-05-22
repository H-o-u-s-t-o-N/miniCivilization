package com.game.miniCivilization.domain.service;

import com.game.miniCivilization.domain.City;
import com.game.miniCivilization.domain.Tile;
import com.game.miniCivilization.domain.Unit;
//import com.game.miniCivilization.domain.units.Archer;
import com.game.miniCivilization.domain.User;
import com.game.miniCivilization.repository.CityRepo;
import com.game.miniCivilization.repository.MainRepo;
import com.game.miniCivilization.repository.UnitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MainService {
//    @Autowired
    private final MainRepo mainRepo;
    public MainService(MainRepo mainRepo) {
        this.mainRepo = mainRepo;
    }
    @Autowired
    private CityRepo cityRepo;
    @Autowired
    private UnitRepo unitRepo;



    public Iterable<Tile> findAllTiles(){
        return mainRepo.findAll();
    }

    public Tile findOnebyId(Long id){
        return mainRepo.findById(id)
            .orElseThrow();
    }

    public void creatCity(Long id){
        Tile temp = mainRepo.findById(id).get();
        City tempCity = new City();
        tempCity.setName(1+"");
        cityRepo.save(tempCity);
        temp.setCity(tempCity);
        mainRepo.save(temp);
    }


    public void moveUnit(Long tileIdStart, Long tileIdEnd){

        Tile tempStart = mainRepo.findById(tileIdStart).get();
        Tile tempEnd = mainRepo.findById(tileIdEnd).get();
        Unit tempUnitA = tempStart.getUnit();
        Unit tempUnitB = tempEnd.getUnit();

        if(tempUnitA != null && tempUnitB != null){
            if(fight(tempUnitA,tempUnitB)){
                tempEnd.setUnit(tempUnitA);
                tempStart.setUnit(null);
                unitRepo.delete(tempUnitB);
                mainRepo.save(tempEnd);
                mainRepo.save(tempStart);
            }

        }else {
            tempEnd.setUnit(tempUnitA);
            tempStart.setUnit(null);
            mainRepo.save(tempEnd);
            mainRepo.save(tempStart);

        }


    }

    public boolean fight(Unit unitA, Unit unitB){
        if(unitB.getHealth()-unitA.getDamage() <= 0){
            return true;
        }else {
            unitB.setHealth(unitB.getHealth() - unitA.getDamage());
            unitRepo.save(unitB);
            return false;
        }

    }

//    public void creatUnit(Long id) {
//        Tile tile = mainRepo.findById(id).get();
//        Unit unit = new Unit();
//        unit.setName(tile.getName());
//        unitRepo.save(unit);
//        tile.setUnit(unit);
//        mainRepo.save(tile);
//    }

    public void creatUnit(Long id, User user) {
        Tile tile = mainRepo.findById(id).get();
        Unit unit = new Unit(user);
        unit.setName(tile.getName());
        unitRepo.save(unit);
        tile.setUnit(unit);
        mainRepo.save(tile);
    }

//    public void creatArcher(Long id) {
//        Tile tile = mainRepo.findById(id).get();
//        Unit archer = new Archer();
//        archer.setName(tile.getName());
//        unitRepo.save(archer);
//        tile.setUnit(archer);
//        mainRepo.save(tile);
//    }
//
//    public void creatWarrior(Long id) {
//        Tile tile = mainRepo.findById(id).get();
//        Unit warrior = new Warrior();
//        warrior.setName(tile.getName());
//        unitRepo.save(warrior);
//        tile.setUnit(warrior);
//        mainRepo.save(tile);
//    }
}
