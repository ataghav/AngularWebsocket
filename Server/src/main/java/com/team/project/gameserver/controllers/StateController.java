package com.team.project.gameserver.controllers;

import com.team.project.gameserver.dtos.State;
import com.team.project.gameserver.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StateController {
    @Autowired
    private StateService stateService;

    @RequestMapping("/state")
    public State getState(){
        return null;
    }
}
