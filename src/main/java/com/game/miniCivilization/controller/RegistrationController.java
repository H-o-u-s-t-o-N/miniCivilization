package com.game.miniCivilization.controller;

import com.game.miniCivilization.domain.Role;
import com.game.miniCivilization.domain.User;
import com.game.miniCivilization.domain.service.UserService;
import com.game.miniCivilization.repository.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    private final UserRepo userRepo;

    public RegistrationController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(User user, Map<String,Object> model){
        User userFromDB = userRepo.findByUsername(user.getUsername());
        if(userFromDB != null){
            model.put("message", "User Exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.ADMIN));
        userRepo.save(user);
        return "redirect:/login";
    }
}
