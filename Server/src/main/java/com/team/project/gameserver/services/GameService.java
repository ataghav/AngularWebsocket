package com.team.project.gameserver.services;

import com.team.project.gameserver.models.Game;
import org.springframework.transaction.annotation.Transactional;

public interface GameService {
    @Transactional
    Game findById(Long id);

    @Transactional
    void updateGame(Long gameId);

    @Transactional
    Game updateGame(Game detachedAndModifiedGame);

    @Transactional
    Game save(Game game);

    long countGames();
}
