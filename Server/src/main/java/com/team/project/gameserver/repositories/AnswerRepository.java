package com.team.project.gameserver.repositories;

import com.team.project.gameserver.models.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository  extends CrudRepository<Answer,Long> {
}
