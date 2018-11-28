package com.team.project.gameserver.services;

import com.team.project.gameserver.models.User;
import com.team.project.gameserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(final Long id){
        return userRepository.findById(id).get();
    }
}
