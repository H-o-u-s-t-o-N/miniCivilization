package com.game.miniCivilization.domain.service;

import com.game.miniCivilization.domain.City;
import com.game.miniCivilization.domain.Player;
import com.game.miniCivilization.domain.Tile;
import com.game.miniCivilization.domain.units.Archer;
import com.game.miniCivilization.domain.units.Warrior;
import com.game.miniCivilization.repository.*;
import org.springframework.stereotype.Service;

@Service
public class CreateService {
    private final TileRepo tileRepo;
    private final CityRepo cityRepo;
    private final UnitRepo unitRepo;

    public CreateService(TileRepo tileRepo, CityRepo cityRepo, UnitRepo unitRepo) {
        this.tileRepo = tileRepo;
        this.cityRepo = cityRepo;
        this.unitRepo = unitRepo;
    }


    public void creatCity(Long id){
        Tile tempTile = tileRepo.findById(id).get();
        City tempCity = new City();
        tempCity.setName(tempTile.getName());
        cityRepo.save(tempCity);
        tempTile.setCity(tempCity);
        tileRepo.save(tempTile);
    }

    public void creatArcher(Long id, Player player) {
        Tile tile = tileRepo.findById(id).get();
        Archer archer = new Archer(player);
        archer.setCoordX(tile.getCoordX());
        archer.setCoordY(tile.getCoordY());
        archer.reName(tile.getName(), player.getUsername());
        unitRepo.save(archer);
        tile.setUnit(archer);
        tileRepo.save(tile);
    }

    public void creatWarrior(Long id, Player player) {
        Tile tile = tileRepo.findById(id).get();
        Warrior warrior = new Warrior(player);
        warrior.setCoordX(tile.getCoordX());
        warrior.setCoordY(tile.getCoordY());
        warrior.reName(tile.getName(), player.getUsername());
        unitRepo.save(warrior);
        tile.setUnit(warrior);
        tileRepo.save(tile);
    }
}
