package com.team.project.gameserver.services;

import com.team.project.gameserver.models.User;

public interface UserService {
    User findById(Long id);
}
