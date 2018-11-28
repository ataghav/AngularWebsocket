package com.team.project.gameserver.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String givenName;
    private String SurName;
    private String username;
    private String passWord;
    private Boolean isReady;
    private Boolean hasAskedFlag;
    private Boolean hasAnsweredFlag;
    private Integer score;

    @OneToMany(mappedBy = "user")
    private List<Answer> answers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurName() {
        return SurName;
    }

    public void setSurName(String surName) {
        SurName = surName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Boolean getReady() {
        return isReady;
    }

    public void setReady(Boolean ready) {
        isReady = ready;
    }

    public Boolean getHasAskedFlag() {
        return hasAskedFlag;
    }

    public void setHasAskedFlag(Boolean hasAskedFlag) {
        this.hasAskedFlag = hasAskedFlag;
    }

    public Boolean getHasAnsweredFlag() {return hasAnsweredFlag;}

    public void setHasAnsweredFlag(Boolean hasAnsweredFlag) {this.hasAnsweredFlag = hasAnsweredFlag;}

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
