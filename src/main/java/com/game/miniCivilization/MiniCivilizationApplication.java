package com.game.miniCivilization;

import com.game.miniCivilization.repository.MainRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiniCivilizationApplication {
	@Autowired
	private MainRepo mainRepo;

	public static void main(String[] args) {
		SpringApplication.run(MiniCivilizationApplication.class, args);
	}

}
