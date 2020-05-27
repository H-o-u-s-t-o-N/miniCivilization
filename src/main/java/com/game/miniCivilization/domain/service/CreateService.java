package com.game.miniCivilization.domain.service;

import com.game.miniCivilization.domain.*;
import com.game.miniCivilization.domain.units.Archer;
import com.game.miniCivilization.domain.units.Colonist;
import com.game.miniCivilization.domain.units.Warrior;
import com.game.miniCivilization.repository.*;
import org.springframework.stereotype.Service;

@Service
public class CreateService {
    private final TileRepo tileRepo;
    private final CityRepo cityRepo;
    private final UnitRepo unitRepo;
    private final GameRepo gameRepo;
    private final PlayerRepo playerRepo;

    public CreateService(TileRepo tileRepo, CityRepo cityRepo, UnitRepo unitRepo, GameRepo gameRepo, PlayerRepo playerRepo) {
        this.tileRepo = tileRepo;
        this.cityRepo = cityRepo;
        this.unitRepo = unitRepo;
        this.gameRepo = gameRepo;
        this.playerRepo = playerRepo;
    }

    public void createGame(Player player){
        Game game = new Game(player);
        player.setCanMakeMove(true);
        playerRepo.save(player);
        gameRepo.save(game);
    }

    public void creatCity(Long id, Player player){
        Tile tempTile = tileRepo.findById(id).get();
        Unit tempUnit = tempTile.getUnit();
        if(tempUnit.getClass() == Colonist.class) {
            City tempCity = new City(player);
            tempCity.setName(player.getUsername());
            cityRepo.save(tempCity);
            tempTile.setUnit(null);
            tempTile.setCity(tempCity);
            unitRepo.delete(tempUnit);
            tileRepo.save(tempTile);
        }
    }

    public void creatColonist(Long id, Player player) {
        Tile tile = tileRepo.findById(id).get();
        Colonist colonist = new Colonist(player);
        colonist.setCoordX(tile.getCoordX());
        colonist.setCoordY(tile.getCoordY());
        colonist.reName(tile.getName(), player.getUsername());
        unitRepo.save(colonist);
        tile.setUnit(colonist);
        tileRepo.save(tile);
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
