package com.team.project.gameserver.services;

import com.team.project.gameserver.models.Question;
import com.team.project.gameserver.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question findById(final Long id){
        return questionRepository.findById(id).get();
    }

    @Override
    public Question save(final Question question) {
        return questionRepository.save(question);
    }
}
