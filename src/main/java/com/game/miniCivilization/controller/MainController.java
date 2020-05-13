package com.game.miniCivilization.controller;

import com.game.miniCivilization.MainService;
import com.game.miniCivilization.domain.Tile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    @Autowired
    private MainService mainService;

//    @GetMapping(value =  {"", "/", "/main"})
    @GetMapping("/main")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "main";
    }
    @GetMapping("/tile")
    public Iterable<Tile> findTiles(){
        return mainService.findAllTiles();
    }
    @PostMapping("/tile")
    public Tile creatTile(@RequestBody Tile tile){
        return mainService.saveTile(tile);
    }
}