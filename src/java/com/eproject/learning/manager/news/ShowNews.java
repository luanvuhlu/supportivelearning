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
import java.util.List;
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
public class ShowNews implements Serializable{
    public int NEWS_IN_PAGE=CtrlNews.NEWS_IN_PAGE;
    private boolean detail=false;
    private int page=1;
    private News news;
    public ShowNews() {
        String newsPara=ActionBean.getParameter("view");
        if(!newsPara.equalsIgnoreCase("")){
            news=CtrlNews.getById(Integer.parseInt(newsPara));
            detail=true;
            return;
        }
        String pagesPara=ActionBean.getParameter("page");
        if(!newsPara.equalsIgnoreCase("")){
            page=Integer.parseInt(pagesPara);
            return;
        }
        detail=false;
    }
    public News getnews() {
        return news;
    }
    public int getTotalNews(){
        return CtrlNews.getAllNews().size();
    }
    public int getFirstPage(){
        return 1;
    }
    public int getLastPage(){
        return getTotalNews()/NEWS_IN_PAGE;
    }
    public int[] getListPage(){
        int[] total=new int[NEWS_IN_PAGE];
        if(NEWS_IN_PAGE>getTotalNews()){
            for(int i=1; i<getTotalNews(); i++){
                total[i]=i;
            }
        }else if(page <NEWS_IN_PAGE-(NEWS_IN_PAGE/2)){
            for(int i=1; i<NEWS_IN_PAGE; i++){
                total[i]=i;
            }
        }
//            else if(page> getTotalNews()-NEWS_IN_PAGE){
//            for(int i=getTotalNews()-NEWS_IN_PAGE-1; i<getTotalNews()+1; i++){
//                total[i]=i;
//            }
//        }else{
//            int firstPage=page-(NEWS_IN_PAGE/2);
//            for(int i=firstPage; i<firstPage+NEWS_IN_PAGE; i++){
//                total[i]=i;
//            }
//        }
        return total;
    }
    public List<News> getNewsLimit(){
        return CtrlNews.getNewsLimit(page);
    }

    public int getPage() {
        return page;
    }
    public List<News> getNewsMore(){
        return CtrlNews.getAllNews();
    }
    public void setnews(News news) {
        this.news = news;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public boolean isDetail() {
        return detail;
    }
}
