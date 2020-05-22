package com.game.miniCivilization.controller;


import com.game.miniCivilization.domain.User;
import com.game.miniCivilization.repository.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@Controller
public class PageController {


    @GetMapping(value =  {"", "/"})
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "main";
    }



}