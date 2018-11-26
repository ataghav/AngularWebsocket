package com.team.project.gameserver.dtos;

import com.team.project.gameserver.Enums.MessageType;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class SocketMessage {
    private MessageType type;
    private String user;
    private Date createdAt;
    private String text;
    private List<String> options;
    private Integer answerIndex;
    private HashMap<String, String> addedScores;

    public static SocketMessage playerSelectedMessage(String user){
        SocketMessage nsm = new SocketMessage();
        nsm.setMessageType(MessageType.PLAYER_SELECTED);
        nsm.setUser(user);
        return nsm;
    }

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
