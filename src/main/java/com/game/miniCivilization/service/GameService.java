package com.game.miniCivilization.service;

import com.game.miniCivilization.domain.*;
import com.game.miniCivilization.domain.enums.Land;
import com.game.miniCivilization.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Arrays.asList;

@Service
public class GameService {
    private CreateService createService;
    @Autowired
    private UnitRepo unitRepo;
    @Autowired
    private GameRepo gameRepo;
    @Autowired
    private CityRepo cityRepo;
    @Autowired
    private PlayerRepo playerRepo;
    @Autowired
    private TileRepo tileRepo;

    public void restoreActionPoint(Player player){
        Iterable<Unit> units = unitRepo.findAllByPlayer(player);
        units.forEach(unit -> {
            unit.setActionPoint(unit.getFinalActionPoint());
            unitRepo.save(unit);
        });
    }

    public void changeMove(Player player){
        Game game = gameRepo.findById(player.getActiveGameId()).get();
        Player playerA = game.getPlayerA();
        Player playerB = game.getPlayerB();
        if(player.getUsername().equals(playerA.getUsername())){
            playerA.setCanMakeMove(false);
            playerB.setCanMakeMove(true);
            playerRepo.saveAll(asList(playerA,playerB));
        }else {
            playerA.setCanMakeMove(true);
            playerB.setCanMakeMove(false);
            playerRepo.saveAll(asList(playerA,playerB));
        }
    }

    public void createGame(String gameName, Player player){
        Game game = new Game(player);
        game.setName(gameName);
        game.setStartMoney();
        gameRepo.save(game);
        player.setActiveGameId(game.getId());
        playerRepo.save(player);
    }

//    public void connectGame(Long gameId, Player player){
//        Game game = gameRepo.findById(gameId).get();
//        if(!game.getPlayerA().getUsername().equals(player.getUsername())){
//            game.setPlayerB(player);
//            gameRepo.save(game);
//            Player playerA = game.getPlayerA();
//            playerA.setCanMakeMove(true);
//            player.setActiveGameId(gameId);
//            playerRepo.saveAll(asList(player,playerA));
//
//            createService.createStartParam(gameRepo.findById(gameId).get());
//            restoreActionPoint(playerA);
//            restoreActionPoint(player);
//        }else {
//            player.setActiveGameId(gameId);
//            playerRepo.save(player);
//        }
//
//    }



//    public void addMoney(Player player){
//        Iterable<City> cities = cityRepo.findAllByPlayer(player);
////      money   set  +=  cities.size() * 200
//
//    }

}
