package com.team.project.gameserver.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String givenName;
    private String SurName;
    private String userName;
    private String passWord;
    private Boolean isReady;
    private Boolean hasAskedFlag;
    private Boolean hasAnsweredFlag;
    private Integer score;

    public User() {
        this.isReady = false;
        this.hasAskedFlag = false;
    }

    public User(String userName) {
        this.userName = userName;
        this.isReady = false;
        this.hasAskedFlag = false;
    }


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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
