package com.game.miniCivilization.domain.service;


import com.game.miniCivilization.repository.PlayerRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PlayerService implements UserDetailsService {
    private final PlayerRepo playerRepo;
    public PlayerService(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return playerRepo.findByUsername(s);
    }

}
