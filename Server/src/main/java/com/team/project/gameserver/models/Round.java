package com.team.project.gameserver.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Round {
    @Id
    private Long id;
//    private Game game;
    private Date startedAt;
    private Date CompletedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Game getGame() {
//        return game;
//    }
//
//    public void setGame(Game game) {
//        this.game = game;
//    }

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
}
