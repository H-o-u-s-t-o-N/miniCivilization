package com.game.miniCivilization;

import com.game.miniCivilization.domain.Tile;
import com.game.miniCivilization.repository.CityRepo;
import com.game.miniCivilization.repository.MainRepo;
import com.game.miniCivilization.repository.UnitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MainService {
    @Autowired
    private MainRepo mainRepo;
    @Autowired
    private CityRepo cityRepo;
    @Autowired
    private UnitRepo unitRepo;

    public Iterable<Tile> findAllTiles(){
        return mainRepo.findAll();
    }

    public Tile saveTile(Tile tile){
        return mainRepo.save(tile);
    }


}
