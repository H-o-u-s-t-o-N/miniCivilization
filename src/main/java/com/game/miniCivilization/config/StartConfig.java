package com.game.miniCivilization.config;

import com.game.miniCivilization.domain.Player;
import com.game.miniCivilization.domain.Tile;
import com.game.miniCivilization.domain.Unit;
import com.game.miniCivilization.repository.TileRepo;
import com.game.miniCivilization.repository.UnitRepo;
import com.game.miniCivilization.repository.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class StartConfig {
    @Autowired
    private UnitRepo unitRepo;
    @Autowired
    private PlayerRepo playerRepo;


    private final TileRepo tileRepo;
    public StartConfig(TileRepo tileRepo) {
        this.tileRepo = tileRepo;
    }

    @PostConstruct
    public void init(){
        int SIDE = 16;
        Long iter = 0L;
        Tile tempTile;
//        City tempCity;
        Unit tempUnit;

//        tempUnit = new Unit();
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                tempTile = new Tile();
                tempTile.setId(iter);
                tempTile.setCoordX(j);
                tempTile.setCoordY(i);
                tempTile.setName(iter+"");



//                if(iter == 5 || iter == 1 || iter == 3){
//                    tempUnit = new ArcherVeteran();
////                    tempUnit.reName(i+"_"+j);
//                    unitRepo.save(tempUnit);
//                    tempTile.setUnit(tempUnit);
//                    System.out.println("ppp");
//                }else{
//                    tempTile.setUnit(null);
//                }


//                Land
                if((int)(Math.random()*10)==9){
                    tempTile.setLand("Mountain");
                }else {
                    tempTile.setLand("Grass");
                }






                tileRepo.save(tempTile);
                iter++;
            }
        }
        Player player = new Player();
        player.setUsername("w");
        player.setPassword("w");
        player.setActive(true);
        playerRepo.save(player);
    }
}
