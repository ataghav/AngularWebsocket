package com.team.project.gameserver.services;

import com.team.project.gameserver.models.Round;
import com.team.project.gameserver.repositories.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoundServiceImpl  implements RoundService{

    @Autowired
    private RoundRepository roundRepository;

    @Override
    public Round save(Round round) {
        return roundRepository.save(round);
    }
}
