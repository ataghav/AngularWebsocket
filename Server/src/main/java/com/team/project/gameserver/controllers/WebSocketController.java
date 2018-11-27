package com.team.project.gameserver.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.team.project.gameserver.dtos.SocketMessage;
import com.team.project.gameserver.models.*;
import com.team.project.gameserver.repositories.GameRepository;
import com.team.project.gameserver.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin
public class WebSocketController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private QuestionRepository questionRepository;

    private Long currentGameId;
    private Long currentRoundId;
    private Long currentQuestionId;

    private List<User> loggedInUsers = new ArrayList<>();
    private Boolean propagationPermit = true;


    @MessageMapping("/message")
    @SendTo("/topic/reply")
    public String processMessageFromClient(@Payload String message) throws Exception {
//        String name = new Gson().fromJson(message, Map.class).get("name").toString();
//        return name;
        if (currentGameId == null) {
            currentGameId = gameRepository.save(new Game() {{
                setStartedAt(new Date());
            }}).getId();
        }
        SocketMessage sm = new Gson().fromJson(message, SocketMessage.class);
        switch (sm.getMessageType()) {
            case PLAYER_JOINED:
                handlePlayerJoined(sm);
                break;
            case PLAYER_LEFT:
                handlePlayerLeft(sm);
                break;

            case PLAYER_READY:
                handlePlayerReady(sm);
                break;

            case QUESTION_SUBMITTED:
                handleQuestionSubmitted(sm);
                break;

            case ANSWER_SUBMITTED:
                handleAnswerSubmitted(sm);
                break;
        }

        if (!propagationPermit) {
            propagationPermit = true;
            return null;
        }

        return message;
    }

    private void handleAnswerSubmitted(SocketMessage sm) {
        Boolean allAnswered = true;
        for (User user : loggedInUsers) {
            if (user.getUserName().equals(sm.getUser())) {
                user.setHasAnsweredFlag(true);
                return;
            }
            if (!user.getHasAnsweredFlag()) {
                allAnswered = false;
            }
        }
        if (allAnswered) {
            // TODO: score players
            declareQuestioner();
        }
    }

    private void handlePlayerJoined(SocketMessage sm) {
        for (User user : loggedInUsers) {
            if (user.getUserName().equals(sm.getUser())) {
                propagationPermit = false;
                return;
            }
        }
        loggedInUsers.add(new User(sm.getUser()));
    }

    private void handlePlayerLeft(SocketMessage sm) {
        propagationPermit = false;
        for (User user : loggedInUsers) {
            if (user.getUserName().equals(sm.getUser())) {
                loggedInUsers.remove(user);
                propagationPermit = true;
            }
        }
    }

    private void handlePlayerReady(SocketMessage sm) {
//        TODO: check weather all the logged in users are ready or not, if so, select questioner and declare her
        int readyUsersCount = 0;

        for (User user : loggedInUsers) {
            if (user.getUserName().equals(sm.getUser())) {
                user.setReady(true);
            }
            if (user.getReady()) {
                readyUsersCount++;
            }
        }
        if (loggedInUsers.size() == readyUsersCount) {
            gameRepository.findById(currentGameId).get().getRounds().add(new Round(){{
                setStartedAt(new Date());
            }});
//            gameRepository.

            declareQuestioner();
        }
    }

    private void handleQuestionSubmitted(SocketMessage sm) {
        // TODO: register question:
        Question question = new Question(){{
            setText(sm.getText());
        }};
//        question.setText(sm.getText());
        List<Option> lo = new ArrayList<>();
        for (String s : sm.getOptions()) {
            lo.add(new Option() {{
                setQuestion(question);
                setText(s);
                setIsTrue(false);
            }});
        }
        question.getOptions().get(sm.getAnswerIndex()).setIsTrue(true);
        question.setOptions(lo);
        currentQuestionId = questionRepository.save(question).getId();
    }

    private void declareQuestioner() {
        int notAskedCount = 0;
        int chosenIndex;
        String chosenUsername = "";
        int i = 0;

        // count how many joined players has not asked their question in current round yet:
        for (User user : loggedInUsers) {
            if (!user.getHasAskedFlag()) {
                notAskedCount++;
            }
        }

        // If all has asked their question once then reset the state and select among all joined users:
        if (notAskedCount == 0) {
            notAskedCount = loggedInUsers.size();
            for (User user : loggedInUsers) {
                user.setHasAskedFlag(false);
            }
        }

        // Choose one among the players who has not asked their question in current round yet :
        chosenIndex = ThreadLocalRandom.current().nextInt(0, notAskedCount);

        for (User user : loggedInUsers) {
            if (i == chosenIndex) {
                user.setHasAskedFlag(true);
                chosenUsername = user.getUserName();
            }
            i++;
        }
//        return  Gson().toJson(SocketMessage.playerSelectedMessage(chosenUsername));
        messagingTemplate.convertAndSend("/topic/reply", new Gson().toJson(SocketMessage.playerSelectedMessage(chosenUsername)));
    }

    @MessageExceptionHandler
    public String handleException(Throwable exception) {
        messagingTemplate.convertAndSend("/errors", exception.getMessage());
        return exception.getMessage();
    }

}