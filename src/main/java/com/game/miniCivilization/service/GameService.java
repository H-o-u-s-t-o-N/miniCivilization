package com.game.miniCivilization.service;

import com.game.miniCivilization.domain.City;
import com.game.miniCivilization.domain.Game;
import com.game.miniCivilization.domain.Unit;
import com.game.miniCivilization.domain.Player;
import com.game.miniCivilization.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Arrays.asList;

@Service
public class GameService {
    @Autowired
    private UnitRepo unitRepo;
    @Autowired
    private GameRepo gameRepo;
    @Autowired
    private CityRepo cityRepo;
    @Autowired
    private PlayerRepo playerRepo;

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

    public void addMoney(Player player){
        Iterable<City> cities = cityRepo.findAllByPlayer(player);
//      money   set  +=  cities.size() * 200


    }

}
