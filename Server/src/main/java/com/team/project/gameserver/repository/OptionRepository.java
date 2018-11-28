package com.team.project.gameserver.repository;

import com.team.project.gameserver.model.Option;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository  extends CrudRepository<Option,Long> {
}
