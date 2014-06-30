/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.news;

import com.eproject.learning.view.*;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author luan
 */
@ManagedBean
public class SearchNews {
    private String title;
    private Date date;
    private Date startDate;
    private Date endDate;

    public SearchNews() {
    }

    public SearchNews(String title, Date date, Date startDate, Date endDate) {
        this.title = title;
        this.date = date;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getDate() {
        return date;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
}
