/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.controller;

import static com.eproject.learning.controller.CtrlAbs.ERROR;
import static com.eproject.learning.controller.CtrlAbs.SUCCESS;
import com.eproject.learning.entity.News;
import com.eproject.learning.manager.news.SearchNews;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author luan
 */
public class CtrlNews extends CtrlAbs{
    public static final int NEWS_IN_PAGE=10;
    public static List<News> getAllNews(){
        Session session=HibernateUtil.getSessionFactory().openSession();
        return (List<News>)session.createCriteria(News.class)
                .addOrder(Order.desc("date"))
                .addOrder(Order.asc("title"))
                .list(); 
    }
    public static List<News> getNewsLimit(int page){
        Session session=HibernateUtil.getSessionFactory().openSession();
        return (List<News>)session.createCriteria(News.class)
                .setFirstResult((page-1)*NEWS_IN_PAGE)
                .setMaxResults(NEWS_IN_PAGE)
                .list(); 
    }
    public static List<News> getShortNews(int number){
        Session session=HibernateUtil.getSessionFactory().openSession();
        return (List<News>)session.createCriteria(News.class)
                .setMaxResults(number)
                .list(); 
    }
    public static News getById(int ID){
        Session session=HibernateUtil.getSessionFactory().openSession();
        News n=(News)session.get(News.class, ID);
        return n;
    }
    public static List<News> search(SearchNews search){
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction tran=null;
        Criteria c=session.createCriteria(News.class);
        if(search==null){
            return null;
        }        
        if(!search.getTitle().trim().equals("")){
            c=c.add(Restrictions.like("title", search.getTitle()));
        }
        if(search.getDate() !=null){
            c=c.add(Restrictions.eq("date", search.getDate()));
        }else{
            if(search.getStartDate()!=null)
                c=c.add(Restrictions.gt("date", search.getStartDate()));
            if(search.getEndDate() !=null)
                c=c.add(Restrictions.lt("date", search.getEndDate()));
        }
        return (List<News>)c.list();
    }
    public static String add(News news){
        Session session=null;
        
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            session.save(news);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            System.out.println("Add News error !");
            ex.printStackTrace();
            session.close();
            return ERROR;
        }
        return SUCCESS;
        
    }
    public static String update(News news){
        Session session=null;
        
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            session.merge(news);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            System.out.println("Update News error !");
            ex.printStackTrace();
            session.close();
            return ERROR;
        }
        return SUCCESS;
    }
    public static String delete(Integer newsId){
        Session session=null;
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            News news=(News) session.get(News.class, newsId);
            session.delete(news);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            ex.printStackTrace();
            System.out.println("Delete news error !");
            return ERROR;
        }
         System.out.println("Deleted");
        return SUCCESS;
    }
}
