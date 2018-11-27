package com.team.project.gameserver.repositories;

import com.team.project.gameserver.models.Round;
import org.springframework.data.repository.CrudRepository;

public interface RoundRepository  extends CrudRepository<Round,Long> {
}
