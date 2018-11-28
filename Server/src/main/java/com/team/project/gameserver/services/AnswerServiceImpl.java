package com.team.project.gameserver.services;

import com.team.project.gameserver.models.Answer;
import com.team.project.gameserver.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Answer save(final Answer answer){
        return answerRepository.save(answer);
    }
}
