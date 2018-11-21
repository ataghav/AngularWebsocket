package com.team.project.gameserver.dtos;

import com.team.project.gameserver.Enums.MessageType;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class SocketMessage {
    private MessageType type;
    private String user;
    private Date createdAt;
    private String question;
    private List<String> options;
    private Integer answerIndex;
    private HashMap<String, String> addedScores;

    public MessageType getMessageType() {
        return type;
    }

    public void setMessageType(MessageType messageType) {
        this.type = messageType;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public Integer getAnswerIndex() {
        return answerIndex;
    }

    public void setAnswerIndex(Integer answerIndex) {
        this.answerIndex = answerIndex;
    }

    public HashMap<String, String> getAddedScores() {
        return addedScores;
    }

    public void setAddedScores(HashMap<String, String> addedScores) {
        this.addedScores = addedScores;
    }
}
