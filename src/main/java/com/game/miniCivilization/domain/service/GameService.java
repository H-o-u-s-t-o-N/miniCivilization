package com.game.miniCivilization.domain.service;

import com.game.miniCivilization.domain.Game;
import com.game.miniCivilization.domain.Unit;
import com.game.miniCivilization.domain.Player;
import com.game.miniCivilization.repository.GameRepo;
import com.game.miniCivilization.repository.UnitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GameService {
    @Autowired
    private UnitRepo unitRepo;
    @Autowired
    private GameRepo gameRepo;

    public void restoreActionPoint(Player player){
        Iterable<Unit> units = unitRepo.findAllByPlayer(player);
        units.forEach(unit -> {
            unit.setActionPoint(unit.getFinalActionPoint());
            unitRepo.save(unit);
        });
    }

}
