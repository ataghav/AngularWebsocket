package com.team.project.gameserver.model;

import javax.persistence.*;

@Entity
public class Option {
    @Id
    @GeneratedValue
    private Long id;
//    private Long questionId;
    private String text;
    private Boolean isTrue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_question")
    private Question question;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Long getQuestionId() {
//        return questionId;
//    }

//    public void setQuestionId(Long questionId) {
//        this.questionId = questionId;
//    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(Boolean aTrue) {
        isTrue = aTrue;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
