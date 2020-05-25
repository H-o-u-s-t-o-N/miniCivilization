package com.game.miniCivilization.controller;


import com.game.miniCivilization.domain.Player;
import com.game.miniCivilization.domain.enums.Role;
import com.game.miniCivilization.domain.service.CreateService;
import com.game.miniCivilization.repository.GameRepo;
import com.game.miniCivilization.repository.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class PageControl {
    @Autowired
    private CreateService createService;
    @Autowired
    private PlayerRepo playerRepo;
    @Autowired
    private GameRepo gameRepo;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(Player player, Map<String,Object> model){
        Player playerFromDB = playerRepo.findByUsername(player.getUsername());
        if(playerFromDB != null){
            model.put("message", "User Exists!");
            return "registration";
        }
        player.setActive(true);
        player.setRoles(Collections.singleton(Role.ADMIN));
        playerRepo.save(player);
        return "redirect:/login";
    }



    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping(value = {"/greeting", "/", ""})
    public String greeting() {
        return "greeting";
    }

    @PostMapping("/createGame")
    public String createGame(
            @AuthenticationPrincipal Player player
    ){
        createService.createGame(player);
        return "redirect:/main";
    }

    @PostMapping("/findGame")
    public String findGame(
            @AuthenticationPrincipal Player player,
            Map<String, Object> model
    ){
        model.put("games",gameRepo.findAll());
        return "findGame";
    }




}