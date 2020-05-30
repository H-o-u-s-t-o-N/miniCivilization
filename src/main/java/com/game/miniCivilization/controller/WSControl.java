//package com.game.miniCivilization.controller;
//
//import com.game.miniCivilization.domain.OnlyForWS.CreateArcher;
//import com.game.miniCivilization.domain.Player;
//import com.game.miniCivilization.domain.Tile;
//import com.game.miniCivilization.repository.TileRepo;
//import com.game.miniCivilization.service.CreateService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestParam;
//
///**
// * Created by HoustoN
// * Date: 5/28/2020
// */
//@Controller
//public class WSControl {
//    @Autowired
//    private CreateService createService;
//    @Autowired
//    private TileRepo tileRepo;
//
//    @MessageMapping("/createArcher")
//    @SendTo("/topic/tiles")
//    public Iterable<Tile> letsSeeWhatHappens(
//            @AuthenticationPrincipal Player player,
//            CreateArcher archer
//    ) throws Exception {
//        createService.creatArcher(Long.parseLong(archer.getTileId()), player);
////        System.out.println(string);
//        return tileRepo.letsFindAll();
//    }
//
//
//}
