package com.game.miniCivilization.repository;

import com.game.miniCivilization.domain.Unit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepo extends CrudRepository<Unit, Long> {
}
