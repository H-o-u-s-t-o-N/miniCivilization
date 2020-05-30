package com.game.miniCivilization.controller;

import com.game.miniCivilization.domain.Player;
import com.game.miniCivilization.domain.Tile;
import com.game.miniCivilization.domain.Unit;
import com.game.miniCivilization.repository.PlayerRepo;
import com.game.miniCivilization.repository.UnitRepo;
import com.game.miniCivilization.service.CreateService;
import com.game.miniCivilization.service.GameService;
import com.game.miniCivilization.repository.TileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @Autowired
    private UnitRepo unitRepo;
    @Autowired
    private PlayerRepo playerRepo;

    @GetMapping("/tiles")
    public Iterable<Tile> findTiles(){
        return tileRepo.letsFindAll();
    }

    @GetMapping("/units")
    public Iterable<Unit> findUnits(){
        return unitRepo.findAll();
    }

    @GetMapping("/isCanMove")
    public boolean canIMakeMove(
            @AuthenticationPrincipal Player player
    ){
        Player tempPlayer = playerRepo.findById(player.getId()).get();
        return tempPlayer.isCanMakeMove();
    }

    @PostMapping("/turnEnd")
    public void turnEnd(
            @AuthenticationPrincipal Player player
    ){
        gameService.restoreActionPoint(player);
        gameService.changeMove(player);
    }
}
