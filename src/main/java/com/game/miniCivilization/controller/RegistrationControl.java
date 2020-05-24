package com.game.miniCivilization.controller;

import com.game.miniCivilization.domain.enums.Role;
import com.game.miniCivilization.domain.Player;
import com.game.miniCivilization.repository.PlayerRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationControl {
    private final PlayerRepo playerRepo;

    public RegistrationControl(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

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
}
