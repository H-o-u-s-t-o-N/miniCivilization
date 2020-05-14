package com.game.miniCivilization.config;

import com.game.miniCivilization.domain.City;
import com.game.miniCivilization.domain.Tile;
import com.game.miniCivilization.repository.CityRepo;
import com.game.miniCivilization.repository.MainRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class MainConfig {
    @Autowired
    private MainRepo mainRepo;
    @Autowired
    private CityRepo cityRepo;

    @PostConstruct
    public void init(){
        int SIDE = 5;
        Object[][] gameField = new Object[SIDE][SIDE];
        Tile tempTile;
        City tempCity;


        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                tempTile = creatTile();
                tempTile.setId(Integer.parseInt(i+""+j));
                tempTile.setCoordX(i);
                tempTile.setCoordY(j);
                tempTile.setName(i+"_"+j);

                tempCity = creatCity();
                tempCity.setId(Integer.parseInt(i+""+j));
                tempCity.setName("City "+ i+"_"+j);
                cityRepo.save(tempCity);
                tempTile.setCity(tempCity);
                gameField[i][j] = tempTile;
                mainRepo.save(tempTile);
            }
        }

    }
    @Bean
    public Tile creatTile(){
        return new Tile();
    }
    @Bean
    public City creatCity(){
        return new City();
    }

}
