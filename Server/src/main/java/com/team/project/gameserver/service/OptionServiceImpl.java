package com.team.project.gameserver.service;

import com.team.project.gameserver.model.Option;
import com.team.project.gameserver.repository.OptionRepository;
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
