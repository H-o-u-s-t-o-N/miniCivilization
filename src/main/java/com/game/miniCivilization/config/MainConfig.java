package com.game.miniCivilization.config;

import com.game.miniCivilization.domain.Tile;
import com.game.miniCivilization.repository.MainRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class MainConfig {
    @Autowired
    private MainRepo mainRepo;

    @PostConstruct
    public void init(){
        int SIDE = 5;
        Object[][] gameField = new Object[SIDE][SIDE];
        Tile temp;
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                temp = creatTile();
                temp.setId(Integer.parseInt(i+""+j));
                temp.setName(i+"."+j);
                gameField[i][j] = temp;
                mainRepo.save(temp);
            }
        }
    }
    @Bean
    public Tile creatTile(){
        return new Tile();
    }


}
