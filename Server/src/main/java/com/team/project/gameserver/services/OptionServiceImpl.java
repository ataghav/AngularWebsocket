package com.team.project.gameserver.services;

import com.team.project.gameserver.models.Option;
import com.team.project.gameserver.repositories.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionServiceImpl implements OptionService {
    @Autowired
    private OptionRepository optionRepository;

    @Override
    public Option save(final Option option){
        return optionRepository.save(option);
    }
}
