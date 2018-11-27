package com.team.project.gameserver.repositories;

import com.team.project.gameserver.models.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository  extends CrudRepository<Question,Long> {
}
