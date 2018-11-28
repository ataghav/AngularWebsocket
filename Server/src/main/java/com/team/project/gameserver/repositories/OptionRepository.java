package com.team.project.gameserver.repositories;

import com.team.project.gameserver.models.Option;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository  extends CrudRepository<Option,Long> {
}
