package com.team.project.gameserver.repositories;

import com.team.project.gameserver.models.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository  extends CrudRepository<Game,Long> {
}
