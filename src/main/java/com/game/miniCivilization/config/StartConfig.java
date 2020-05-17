package com.game.miniCivilization.config;

import com.game.miniCivilization.domain.City;
import com.game.miniCivilization.domain.Tile;
import com.game.miniCivilization.domain.Unit;
import com.game.miniCivilization.repository.CityRepo;
import com.game.miniCivilization.repository.MainRepo;
import com.game.miniCivilization.repository.UnitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class StartConfig {
    @Autowired
    private MainRepo mainRepo;
    @Autowired
    private CityRepo cityRepo;
    @Autowired
    private UnitRepo unitRepo;

    @PostConstruct
    public void init(){
        int SIDE = 16;
        int iter = 0;
        int tempRandomNumber = (int)Math.random()*254;
        Tile tempTile;
        City tempCity;
        Unit tempUnit;

        tempUnit = new Unit();
        tempUnit.setId(0);
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                tempTile = new Tile();
                tempTile.setId((long)iter);
//                System.out.println(tempTile.getId());
//                tempTile.setCoordX(i);
//                tempTile.setCoordY(j);
                tempTile.setName(i+"_"+j);
                if(iter == 10){
                    tempUnit.setName(i+"_"+j);
                    unitRepo.save(tempUnit);
                    tempTile.setUnit(tempUnit);
//                    System.out.println("ppp");
                }else{
                    tempTile.setUnit(null);
                }

//                Land
                if((int)(Math.random()*10)==9){
                    tempTile.setLand("Mountain");
                }else {
                    tempTile.setLand("Grass");
                }






//                    City
//                    tempCity = createCity();
//                    tempCity.setId((long)iter);
//                    tempCity.setName(i+"_"+j);
//                    cityRepo.save(tempCity);
//                    tempTile.setCity(tempCity);
//                    Unit
//                    tempUnit = createUnit();
//                    tempUnit.setId((long)iter);
//                    tempUnit.setName(i+"_"+j);
//                    unitRepo.save(tempUnit);
//                    tempTile.setUnit(tempUnit);
                mainRepo.save(tempTile);
                iter++;
            }
        }
    }
}
