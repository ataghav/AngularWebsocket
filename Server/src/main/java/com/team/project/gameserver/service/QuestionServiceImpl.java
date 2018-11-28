package com.team.project.gameserver.service;

import com.team.project.gameserver.model.Question;
import com.team.project.gameserver.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question save(final Question question) {
        return questionRepository.save(question);
    }
}
