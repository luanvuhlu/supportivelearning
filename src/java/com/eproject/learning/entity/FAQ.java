/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eproject.learning.entity;


import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Ncuong
 */
@ManagedBean
@Entity
@Table(name = "faq")
public class FAQ implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "FAQ_ID", unique = true, nullable = false)
    private int fAQId;
    @Column(name = "Answer")
    private String answer;
    @Column(name = "Question")
    private String question;
    @Column(name = "Date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;

    public FAQ() {

    }

    public FAQ(int fAQId, String answer, String question, Date date) {
        this.fAQId = fAQId;
        this.answer = answer;
        this.question = question;
        this.date = date;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getfAQId() {
        return fAQId;
    }

    public void setfAQId(int fAQId) {
        this.fAQId = fAQId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getFAQId() {
        return fAQId;
    }

    public void setFAQId(int fAQId) {
        this.fAQId = fAQId;
    }
}
