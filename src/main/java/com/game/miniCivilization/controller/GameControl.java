package com.game.miniCivilization.controller;

import com.game.miniCivilization.domain.Player;
import com.game.miniCivilization.domain.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameControl {
    @Autowired
    private GameService gameService;

    @PostMapping
    public void restoreActionPoints(
            @AuthenticationPrincipal Player player
    ){
        gameService.restoreActionPoint(player);
    }
}
