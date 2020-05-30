package com.game.miniCivilization;

import com.game.miniCivilization.domain.Tile;
import com.game.miniCivilization.domain.Unit;
import com.game.miniCivilization.domain.units.Archer;
import com.game.miniCivilization.repository.TileRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class MiniCivilizationApplicationTests {
		@Autowired
		private TileRepo tileRepo;
	@Test
	void contextLoads() {
		Tile tile = new Tile();
		tile.setCoordX(5);
		tile.setCoordY(5);

		System.out.println(tileRepo.findByCoordinats(5,5).getName());
	}

	@Test
	void checkClass(){
		Unit unit = new Archer();
		if(unit.getClass() == Archer.class)
			System.out.println("yes, its working");
		else
			System.out.println("Fuuu****K");
	}
	@Test
	void orderById(){
		Iterable<Tile> tiles = tileRepo.letsFindAll();
		tiles.forEach(tile -> System.out.println(tile.getId()));
	}

}
