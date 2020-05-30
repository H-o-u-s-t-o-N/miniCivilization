package com.game.miniCivilization.repository;

import com.game.miniCivilization.domain.City;
import com.game.miniCivilization.domain.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepo extends CrudRepository<City, Long> {

    Iterable<City> findAllByPlayer(Player player);
}
