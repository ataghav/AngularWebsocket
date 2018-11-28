package com.team.project.gameserver.repository;

import com.team.project.gameserver.model.Round;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepository  extends CrudRepository<Round,Long> {
}
