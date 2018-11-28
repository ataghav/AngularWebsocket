package com.team.project.gameserver.service;

import com.team.project.gameserver.model.Game;
import com.team.project.gameserver.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;

    @Override
    @Transactional
    public Game findById(final Long id){
        return gameRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void  updateGame(final Long gameId){
        final Game game= gameRepository.findById(gameId).get();
        gameRepository.save(game);
    }

    @Override
    @Transactional
    public Game  updateGame(final Game detachedAndModifiedGame){
        return gameRepository.save(detachedAndModifiedGame);
    }

    @Override
    @Transactional
    public Game save(final Game game){
        return gameRepository.save(game);
    }

    @Override
    public long countGames() {
        return gameRepository.count();
    }
}
