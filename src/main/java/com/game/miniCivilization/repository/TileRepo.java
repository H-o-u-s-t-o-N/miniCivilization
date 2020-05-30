package com.game.miniCivilization.repository;

import com.game.miniCivilization.domain.Tile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TileRepo extends CrudRepository<Tile, Long> {
    @Query("from Tile t order by t.id")
    Iterable<Tile> letsFindAll();

    @Query("from Tile t where t.coordX = :coordX and t.coordY = :coordY")
    Tile findByCoordinats(
            @Param("coordX") int coordX,
            @Param("coordY") int coordY);
}
