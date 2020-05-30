package com.game.miniCivilization.service;

import com.game.miniCivilization.domain.*;
import com.game.miniCivilization.domain.enums.Land;
import com.game.miniCivilization.domain.units.Archer;
import com.game.miniCivilization.domain.units.Colonist;
import com.game.miniCivilization.domain.units.Warrior;
import com.game.miniCivilization.repository.*;
import org.springframework.stereotype.Service;

import static java.util.Arrays.asList;

@Service
public class CreateService {
    private final TileRepo tileRepo;
    private final CityRepo cityRepo;
    private final UnitRepo unitRepo;
    private final GameRepo gameRepo;
    private final PlayerRepo playerRepo;
    private final GameService gameService;

    public CreateService(TileRepo tileRepo, CityRepo cityRepo, UnitRepo unitRepo, GameRepo gameRepo, PlayerRepo playerRepo, GameService gameService) {
        this.tileRepo = tileRepo;
        this.cityRepo = cityRepo;
        this.unitRepo = unitRepo;
        this.gameRepo = gameRepo;
        this.playerRepo = playerRepo;
        this.gameService = gameService;
    }

    public void createGame(String gameName, Player player){
        Game game = new Game(player);
        game.setName(gameName);
        game.setStartMoney();
        gameRepo.save(game);
        player.setActiveGameId(game.getId());
        playerRepo.save(player);
    }

    public void connectGame(Long gameId, Player player){
        Game game = gameRepo.findById(gameId).get();
        if(!game.getPlayerA().getUsername().equals(player.getUsername())){
            game.setPlayerB(player);
            gameRepo.save(game);
            Player playerA = game.getPlayerA();
            playerA.setCanMakeMove(true);
            player.setActiveGameId(gameId);
            playerRepo.saveAll(asList(player,playerA));

            createStartParam(gameRepo.findById(gameId).get());
            gameService.restoreActionPoint(playerA);
            gameService.restoreActionPoint(player);
        }else {
            player.setActiveGameId(gameId);
            playerRepo.save(player);
        }

    }

    private void createStartParam(Game game) {
        Player playerA = game.getPlayerA();
        Player playerB = game.getPlayerB();
        City cityA = new City(playerA);
        City cityB = new City(playerB);
        cityRepo.saveAll(asList(cityA,cityB));

        Tile tileACity = tileRepo.findById(34L).get();
        tileACity.setLand(Land.Grass);
        tileACity.setCity(cityA);


        createArcher(50L,playerA);
        createColonist(35L,playerA);
        createWarrior(51L,playerA);


        Tile tileBCity = tileRepo.findById(221L).get();
        tileBCity.setLand(Land.Grass);
        tileBCity.setCity(cityB);

        createArcher(205L,playerB);
        createColonist(220L,playerB);
        createWarrior(204L,playerB);




        tileRepo.saveAll(asList(tileACity,tileBCity));
    }

    public void createCity(Long id, Player player){
        Tile tempTile = tileRepo.findById(id).get();
        Unit tempUnit = tempTile.getUnit();
        if(tempUnit.getClass() == Colonist.class) {
            if(tempUnit.getPlayer().getUsername().equals(player.getUsername())) {
                City tempCity = new City(player);
                cityRepo.save(tempCity);
                tempTile.setUnit(null);
                tempTile.setCity(tempCity);
                unitRepo.delete(tempUnit);
                tileRepo.save(tempTile);
            }
        }
    }

    public void createColonist(Long id, Player player) {
        Tile tile = tileRepo.findById(id).get();
        Colonist colonist = new Colonist(player);
        colonist.setCoordX(tile.getCoordX());
        colonist.setCoordY(tile.getCoordY());
        colonist.reName(player.getUsername());
        unitRepo.save(colonist);
        tile.setUnit(colonist);
        tileRepo.save(tile);
    }

    public void createArcher(Long id, Player player) {
        Tile tile = tileRepo.findById(id).get();
        Archer archer = new Archer(player);
        archer.setCoordX(tile.getCoordX());
        archer.setCoordY(tile.getCoordY());
        archer.reName(player.getUsername());
        unitRepo.save(archer);
        tile.setUnit(archer);
        tileRepo.save(tile);
    }

    public void createWarrior(Long id, Player player) {
        Tile tile = tileRepo.findById(id).get();
        Warrior warrior = new Warrior(player);
        warrior.setCoordX(tile.getCoordX());
        warrior.setCoordY(tile.getCoordY());
        warrior.reName(player.getUsername());
        unitRepo.save(warrior);
        tile.setUnit(warrior);
        tileRepo.save(tile);
    }
}
