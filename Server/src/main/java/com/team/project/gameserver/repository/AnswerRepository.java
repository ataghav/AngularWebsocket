package com.team.project.gameserver.repository;

import com.team.project.gameserver.model.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository  extends CrudRepository<Answer,Long> {
}
