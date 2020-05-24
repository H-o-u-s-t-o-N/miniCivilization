package com.game.miniCivilization.repository;

import com.game.miniCivilization.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Long> {
    Player findByUsername(String username);
}
