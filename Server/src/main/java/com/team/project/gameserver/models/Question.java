package com.team.project.gameserver.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Question {
    @Id
    private Long id;
//    private Round round;
//    private User user;
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Round getRound() {
//        return round;
//    }
//
//    public void setRound(Round round) {
//        this.round = round;
//    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
