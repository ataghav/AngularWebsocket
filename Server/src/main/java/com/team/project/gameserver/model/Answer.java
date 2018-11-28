package com.team.project.gameserver.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Answer {
    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private Long questionId;
    private Long selectedOptionId;
    private Date submittedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_question")
    private Question question;

    public Answer(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getSelectedOptionId() {
        return selectedOptionId;
    }

    public void setSelectedOptionId(Long selectedOptionId) {
        this.selectedOptionId = selectedOptionId;
    }

    public Date getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Date submittedAt) {
        this.submittedAt = submittedAt;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
