package com.team.project.gameserver.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Option {
    @Id
    private Long id;
//    private Question question;
    private String text;
    private Boolean isTrue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Question getQuestion() {
//        return question;
//    }
//
//    public void setQuestion(Question question) {
//        this.question = question;
//    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getTrue() {
        return isTrue;
    }

    public void setTrue(Boolean aTrue) {
        isTrue = aTrue;
    }
}
