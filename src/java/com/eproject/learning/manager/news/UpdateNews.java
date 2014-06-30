/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.news;

import com.eproject.learning.controller.CtrlNews;
import com.eproject.learning.entity.News;
import com.eproject.learning.view.ActionBean;
import java.io.Serializable;
import java.util.Date;
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
public class UpdateNews implements Serializable{
    private News news;
    private boolean addMode=false;
    public UpdateNews() {
        String newsPara=ActionBean.getParameter("n");
        if(ActionBean.getParameter("a").equals("add")){
            System.out.println("Add");
            addMode=true;
            news=new News();
        }else if(newsPara.equalsIgnoreCase("")){
            ActionBean.redirect("manager.xhtml?action=news");
            return;
        }else {
            news=CtrlNews.getById(Integer.parseInt(newsPara));
        }
    }
    public void save(){
        boolean success = false;
        RequestContext context = RequestContext.getCurrentInstance();
        String msg = "";
        String title = "";
        FacesMessage fMsg = null;
        String result = "";
        if (news == null) {
            msg = "Error";
            fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        } else if (news.getTitle().trim().equals("")) {
            msg = "Title is required !";
            fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        } else if (news.getContent().trim().equals("")) {
            msg = "Content is required !";
            fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        } else {
            if (news.getNewsId() == 0) {
                title = "Add News";
                news.setDate(new Date());
                result = CtrlNews.add(news);
            } else {
                title = "Edit News";
                result = CtrlNews.update(news);
            }
            if (result.equals(CtrlNews.SUCCESS)) {
                msg = "Success";
                fMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg);
                success = true;
                reset();
            } else {
                if (result.equals(CtrlNews.ERROR)) {
                    msg = "Error";
                    fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
                }
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, fMsg);
        context.addCallbackParam("update", success);
        
 
    }
    public void reset(){
        if(addMode){
            news=new News();
            return;
        }
        news=CtrlNews.getById(news.getNewsId());
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
    
   
}
