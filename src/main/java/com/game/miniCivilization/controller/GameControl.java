package com.game.miniCivilization.controller;

import com.game.miniCivilization.domain.Player;
import com.game.miniCivilization.domain.Tile;
import com.game.miniCivilization.domain.service.CreateService;
import com.game.miniCivilization.domain.service.GameService;
import com.game.miniCivilization.repository.TileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameControl {
    @Autowired
    private GameService gameService;
    @Autowired
    private CreateService createService;
    @Autowired
    private TileRepo tileRepo;

    @GetMapping("/tile")
    public Iterable<Tile> findTiles(){
        return tileRepo.findAll();
    }

    @GetMapping("/isCanMove")
    public boolean canIMakeMove(
            @AuthenticationPrincipal Player player
    ){
        return player.isCanMakeMove();
    }

    @PostMapping
    public void restoreActionPoints(
            @AuthenticationPrincipal Player player
    ){
        gameService.restoreActionPoint(player);
    }
}
