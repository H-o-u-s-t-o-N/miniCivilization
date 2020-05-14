package com.game.miniCivilization.controller;

import com.game.miniCivilization.MainService;
import com.game.miniCivilization.domain.Tile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControl {
    @Autowired
    private MainService mainService;
    @GetMapping("/tile")
    public Iterable<Tile> findTiles(){
        return mainService.findAllTiles();
    }
    @PostMapping("/tile")
    public Tile creatTile(@RequestBody Tile tile){
        return mainService.saveTile(tile);
    }
}
