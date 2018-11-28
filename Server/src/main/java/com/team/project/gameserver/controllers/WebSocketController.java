package com.team.project.gameserver.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.team.project.gameserver.dtos.SocketMessage;
import com.team.project.gameserver.models.*;
import com.team.project.gameserver.services.*;
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
    private UserService userService;

    @Autowired
    private GameService gameService;

    @Autowired
    private RoundService roundService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private OptionService optionService;

    @Autowired
    private AnswerService answerService;

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
            Game g = new Game();
            g.setStartedAt(new Date());
            currentGameId = gameService.save(g).getId();
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
        Answer a = new Answer();
        a.setUser(userService.findById(Long.parseLong(sm.getUser())));
        a.setQuestion(questionService.findById(currentQuestionId));
        a.setSelectedOptionIndex(sm.getAnswerIndex());
        a.setSubmittedAt(new Date());
        answerService.save(a);

        Boolean allAnswered = true;
        for (User user : loggedInUsers) {
            if (user.getUsername().equals(sm.getUser())) {
                user.setHasAnsweredFlag(true);
            }
            if (!user.getHasAnsweredFlag()) {
                allAnswered = false;
            }
        }
        if (allAnswered) {
            for (User user : loggedInUsers) {
                user.setHasAnsweredFlag(false);
            }
            // TODO: score players
            declareQuestioner();
        }
    }

    private void handlePlayerJoined(SocketMessage sm) {
        for (User user : loggedInUsers) {
            if (user.getUsername().equals(sm.getUser())) {
                propagationPermit = false;
                return;
            }
        }
        User u = new User();
        u.setUsername(sm.getUser());
        u.setReady(false);
        u.setHasAskedFlag(false);
        u.setHasAnsweredFlag(false);
        loggedInUsers.add(u);
    }

    private void handlePlayerLeft(SocketMessage sm) {
        propagationPermit = false;
        for (User user : loggedInUsers) {
            if (user.getUsername().equals(sm.getUser())) {
                loggedInUsers.remove(user);
                propagationPermit = true;
            }
        }
    }

    private void handlePlayerReady(SocketMessage sm) {
        // check weather all the logged in users are ready or not, if so, select questioner and declare her
        int readyUsersCount = 0;

        for (User user : loggedInUsers) {
            if (user.getUsername().equals(sm.getUser())) {
                user.setReady(true);
            }
            if (user.getReady()) {
                readyUsersCount++;
            }
        }
        if (loggedInUsers.size() == readyUsersCount) {
            Round r = new Round();
            r.setStartedAt(new Date());
            r.setGame(gameService.findById(currentGameId));
            currentRoundId = roundService.save(r).getId();

            declareQuestioner();
        }
    }

    private void handleQuestionSubmitted(SocketMessage sm) {
        Question q = new Question();
        q.setText(sm.getText());
        currentQuestionId = questionService.save(q).getId();

        int i = 0;

        for (String s : sm.getOptions()) {
            Option o = new Option();
            o.setQuestion(q);
            o.setText(s);
            if (sm.getAnswerIndex() == i) {
                o.setIsTrue(true);
            }

            optionService.save(o);

            i++;
        }
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
            // TODO: Renew round
        }

        // Choose one among the players who has not asked their question in current round yet :
        chosenIndex = ThreadLocalRandom.current().nextInt(0, notAskedCount);

        System.out.println("[INFO] Selected index is:" + chosenIndex);

        for (User user : loggedInUsers) {
            if (!user.getHasAskedFlag()) {
                if (i == chosenIndex) {
                    user.setHasAskedFlag(true);

                    // a user will not answer his question, so it flaged up here
                    user.setHasAnsweredFlag(true);
                    chosenUsername = user.getUsername();
                }
                i++;
            }
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