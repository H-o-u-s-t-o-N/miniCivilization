package com.game.miniCivilization.controller;

import com.game.miniCivilization.MainService;
import com.game.miniCivilization.domain.Tile;
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
    @PostMapping
    public Tile creatTile(@RequestBody Tile tile){
        return mainService.saveTile(tile);
    }
    @PostMapping("/moveUnit")
//  postman http://localhost:8080/tile/moveUnit?idStart=10&idEnd=5     NULL
    public void moveUnit(@PathParam("idStart") Long tileIdStart,
                         @PathParam("idEnd") Long tileIdEnd){
        mainService.moveUnit(tileIdStart,tileIdEnd);
    }
    @PutMapping("/creatCity/{idTile}")
    public void  createCity(@PathVariable Long idTile){
        mainService.creatCity(idTile);
    }

    @PutMapping("/creatUnit/{idTile}")
    public void  createUnit(@PathVariable Long idTile){
        mainService.creatUnit(idTile);
    }
}
