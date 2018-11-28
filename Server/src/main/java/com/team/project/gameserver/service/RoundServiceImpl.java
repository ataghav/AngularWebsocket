package com.team.project.gameserver.service;

import com.team.project.gameserver.model.Round;
import com.team.project.gameserver.repository.RoundRepository;
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
