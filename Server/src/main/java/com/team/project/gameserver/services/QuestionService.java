package com.team.project.gameserver.services;

import com.team.project.gameserver.models.Question;

public interface QuestionService {

    Question findById(final Long id);

    Question save(final Question question);
}
