package com.team.project.gameserver.repositories;

import com.team.project.gameserver.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
