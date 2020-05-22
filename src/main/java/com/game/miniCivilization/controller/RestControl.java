package com.game.miniCivilization.controller;

import com.game.miniCivilization.domain.User;
import com.game.miniCivilization.domain.service.MainService;
import com.game.miniCivilization.domain.Tile;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tile")
public class RestControl {
    @Autowired
    private MainService mainService;
    @GetMapping
    public Iterable<Tile> findTiles(){
        return mainService.findAllTiles();
    }
    @GetMapping("/{id}")
    public Tile findOne(@PathVariable Long id){
        return mainService.findOnebyId(id);
    }

    @PostMapping("/moveUnit")
    public void moveUnit(
            @RequestParam(value = "idStart") @NonNull Long tileIdStart,
            @RequestParam(value = "idEnd") @NonNull Long tileIdEnd){
        mainService.moveUnit(tileIdStart,tileIdEnd);
    }
    @PutMapping("/createCity/{idTile}")
    public void  createCity(@PathVariable Long idTile){
        mainService.creatCity(idTile);
    }

    @PostMapping("/createUnit")
    public void createUnit(
            @AuthenticationPrincipal User user,
            @RequestParam(value = "idTile") @NonNull Long tileId){
        mainService.creatUnit(tileId, user);
//        mainService.creatUnit(tileId);
    }
//    @PostMapping("/createArcher")
//    public void createArcher(@RequestParam(value = "idTile") @NonNull Long tileId){
//        mainService.creatArcher(tileId);
//    }
//
//    @PostMapping("/createWarrior")
//    public void createWarrior(@RequestParam(value = "idTile") @NonNull Long tileId){
//        mainService.creatWarrior(tileId);
//    }
}
