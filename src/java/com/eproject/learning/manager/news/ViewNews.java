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

/**
 *
 * @author luan
 */
@ManagedBean
@ViewScoped
@SessionScoped
public class ViewNews implements Serializable{
    private News news;
    public ViewNews() {
        String newsPara=ActionBean.getParameter("n");
        if(newsPara.equalsIgnoreCase("")){
            ActionBean.redirect("manager.xhtml?action=news");
            return;
        }else {
            news=CtrlNews.getById(Integer.parseInt(newsPara));
        }
    }
    public News getnews() {
        return news;
    }

    public void setnews(News news) {
        this.news = news;
    }
    
   
}
