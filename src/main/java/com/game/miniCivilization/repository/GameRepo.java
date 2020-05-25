package com.game.miniCivilization.repository;


import com.game.miniCivilization.domain.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepo extends CrudRepository<Game,Long> {
}
