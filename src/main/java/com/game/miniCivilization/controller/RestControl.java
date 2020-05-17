package com.game.miniCivilization.controller;

import com.game.miniCivilization.MainService;
import com.game.miniCivilization.domain.Tile;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

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
    public void moveUnit(@RequestParam(value = "idStart") @NonNull Long tileIdStart,
                         @RequestParam(value = "idEnd") @NonNull Long tileIdEnd){
        mainService.moveUnit(tileIdStart,tileIdEnd);
    }
    @PutMapping("/createCity/{idTile}")
    public void  createCity(@PathVariable Long idTile){
        mainService.creatCity(idTile);
    }

    @PostMapping("/createUnit")
    public void  createUnit(@RequestParam(value = "idTile") @NonNull Long tileId){
        mainService.creatUnit(tileId);
    }
}
