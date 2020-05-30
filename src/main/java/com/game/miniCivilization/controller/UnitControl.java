package com.game.miniCivilization.controller;

import com.game.miniCivilization.domain.Player;
import com.game.miniCivilization.domain.Tile;
import com.game.miniCivilization.repository.PlayerRepo;
import com.game.miniCivilization.repository.TileRepo;
import com.game.miniCivilization.service.CreateService;
import com.game.miniCivilization.service.UnitService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/unit")
public class UnitControl {
    @Autowired
    private CreateService createService;
    @Autowired
    private UnitService unitService;
    @Autowired
    private TileRepo tileRepo;
    @Autowired
    private PlayerRepo playerRepo;


    @PostMapping("/createCity")
    public void createCity(
            @AuthenticationPrincipal Player player,
            @RequestParam(value = "idTile") Long tileId){
        Player playerDB = playerRepo.findByUsername(player.getUsername());
        if(playerDB.isCanMakeMove()) {
            createService.createCity(tileId, player);
        }
    }

    @PostMapping("/createColonist")
    public void createColonist(
            @AuthenticationPrincipal Player player,
            @RequestParam(value = "idTile") Long tileId){
        Tile tile = tileRepo.findById(tileId).get();
        Player playerDB = playerRepo.findByUsername(player.getUsername());
        if(playerDB.isCanMakeMove()) {
            if (tile.getCity() != null) {
                if (tile.getCity().getPlayer().getUsername().equals(player.getUsername()))
                    createService.createColonist(tileId, player);
            }
        }
    }

    @PostMapping("/createArcher")
    public void createArcher(
            @AuthenticationPrincipal Player player,
            @RequestParam(value = "idTile") Long tileId){
        Tile tile = tileRepo.findById(tileId).get();
        Player playerDB = playerRepo.findByUsername(player.getUsername());
        if(playerDB.isCanMakeMove()) {
            if (tile.getCity() != null) {
                if (tile.getCity().getPlayer().getUsername().equals(player.getUsername()))
                    createService.createArcher(tileId, player);
            }
        }
    }

    @PostMapping("/createWarrior")
    public void createWarrior(
            @AuthenticationPrincipal Player player,
            @RequestParam(value = "idTile") Long tileId){
        Tile tile = tileRepo.findById(tileId).get();
        Player playerDB = playerRepo.findByUsername(player.getUsername());
        if(playerDB.isCanMakeMove()) {
            if (tile.getCity() != null) {
                if (tile.getCity().getPlayer().getUsername().equals(player.getUsername()))
                    createService.createWarrior(tileId, player);
            }
        }
    }

    @PostMapping("/moveUnit")
    public void moveUnit(
            @AuthenticationPrincipal Player player,
            @RequestParam(value = "idStart") @NonNull Long tileIdStart,
            @RequestParam(value = "idEnd") @NonNull Long tileIdEnd){
        Player playerDB = playerRepo.findByUsername(player.getUsername());
        if(playerDB.isCanMakeMove()) {
            unitService.moveUnit(tileIdStart, tileIdEnd, player);
        }
    }
}
