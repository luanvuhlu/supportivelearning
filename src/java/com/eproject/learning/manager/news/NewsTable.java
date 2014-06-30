/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eproject.learning.manager.news;

import com.eproject.learning.controller.CtrlNews;
import com.eproject.learning.controller.CtrlNews;
import com.eproject.learning.entity.News;
import com.eproject.learning.entity.News;
import com.eproject.learning.manager.news.NewsDataModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author luan
 */
@ManagedBean
@ViewScoped
@SessionScoped
public class NewsTable implements Serializable {

    private boolean disable = true;
    private List<News> newss = CtrlNews.getAllNews();
    private News[] selectedNewss;
    private NewsDataModel mediumNewssModel;
    
    private Date date;
    private String title;
    private Date startDate;
    private Date endDate;
    public NewsTable() {
        mediumNewssModel = new NewsDataModel(newss);
        
    }

    public void delete() {
        String result = "";
        List<News> failLs = new ArrayList<>();
        FacesMessage message = null;
        for (News news : selectedNewss) {
            result = CtrlNews.delete(news.getNewsId());
            if (result.equals(CtrlNews.ERROR)) {
                failLs.add(news);
            }
        }
        if (failLs.size() > 0) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete error", failLs.toString() + " are'nt deleted.\nPlease try again !");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Infomation", "Delete success");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        updateNewsList();
    }
    
    public List<News> getShortNews(){
        return CtrlNews.getShortNews(5);
    }
    
    public void searchSubmit(){
        SearchNews s=new SearchNews(title, date, startDate, endDate);
        mediumNewssModel=new NewsDataModel(CtrlNews.search(s));
    }

    private void updateNewsList() {
        mediumNewssModel = new NewsDataModel(CtrlNews.getAllNews());
    }

    public void select() {
        if (selectedNewss == null || selectedNewss.length < 1) {
            disable = true;
        } else {
            disable = false;
        }
    }


    public List<News> getNewss() {
        return newss;
    }

    public NewsDataModel getMediumNewssModel() {
        return mediumNewssModel;
    }

    public News[] getSelectedNewss() {
        return selectedNewss;
    }

    public void setNewss(List<News> newss) {
        this.newss = newss;
    }

    public void setMediumNewssModel(NewsDataModel mediumNewssModel) {
        this.mediumNewssModel = mediumNewssModel;
    }

    public void setSelectedNewss(News[] selectedNewss) {
        this.selectedNewss = selectedNewss;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public boolean isDisable() {
        return disable;
    }

    public Date getDate() {
        return date;
    }

    public Date getEndDate() {
        return endDate;
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

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
