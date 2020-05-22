package com.game.miniCivilization;

import com.game.miniCivilization.domain.Tile;
import com.game.miniCivilization.repository.MainRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class MiniCivilizationApplication {

	public static void main(String[] args) throws Throwable {
		SpringApplication.run(MiniCivilizationApplication.class, args);
	}

}
