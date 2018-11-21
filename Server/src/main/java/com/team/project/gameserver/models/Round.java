package com.team.project.gameserver.models;

import javax.persistence.*;
import javax.websocket.OnError;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Round {
    @Id
    @GeneratedValue
    private Long id;
    private Date startedAt;
    private Date CompletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_game")
    private Game game;

    @OneToMany(mappedBy = "round")
    private List<Question> questions=new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getCompletedAt() {
        return CompletedAt;
    }

    public void setCompletedAt(Date completedAt) {
        CompletedAt = completedAt;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
