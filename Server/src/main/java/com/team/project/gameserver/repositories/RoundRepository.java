package com.team.project.gameserver.repositories;

import com.team.project.gameserver.models.Round;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepository  extends CrudRepository<Round,Long> {
}
