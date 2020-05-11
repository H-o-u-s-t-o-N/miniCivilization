package com.game.miniCivilization.repository;

import com.game.miniCivilization.domain.Tile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainRepo extends CrudRepository<Tile, Long> {
}
