package com.game.miniCivilization.controller;

import com.game.miniCivilization.domain.Player;
import com.game.miniCivilization.domain.service.CreateService;
import com.game.miniCivilization.domain.service.UnitService;
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
//    @Autowired
//    private MainService mainService;
//
//    @GetMapping
//    public Iterable<Archer> findArchers(){
//        return mainService.findAllUnits();
//    }
//
//
//    @GetMapping("/{id}")
//    public Unit findUnit(@PathVariable Long id){
//        return mainService.findUnit(id);
//    }
    @PostMapping("/createCity")
    public void createCity(
            @AuthenticationPrincipal Player player,
            @RequestParam(value = "idTile") Long tileId){
        createService.creatCity(tileId, player);
    }

    @PostMapping("/createArcher")
    public void createArcher(
            @AuthenticationPrincipal Player player,
            @RequestParam(value = "idTile") Long tileId){
        createService.creatArcher(tileId, player);
    }

    @PostMapping("/createWarrior")
    public void createWarrior(
            @AuthenticationPrincipal Player player,
            @RequestParam(value = "idTile") Long tileId){
        createService.creatWarrior(tileId, player);
    }

    @PostMapping("/moveUnit")
    public void moveUnit(
            @AuthenticationPrincipal Player player,
            @RequestParam(value = "idStart") @NonNull Long tileIdStart,
            @RequestParam(value = "idEnd") @NonNull Long tileIdEnd){
        unitService.moveUnit(tileIdStart,tileIdEnd, player);
    }
}
