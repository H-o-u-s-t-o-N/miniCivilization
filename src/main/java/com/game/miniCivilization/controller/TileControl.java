package com.game.miniCivilization.controller;

import com.game.miniCivilization.domain.Tile;
import com.game.miniCivilization.repository.TileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tile")
public class TileControl {
    @Autowired
    private TileRepo tileRepo;

    @GetMapping
    public Iterable<Tile> findTiles(){
        return tileRepo.findAll();
    }
}
