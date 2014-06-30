/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.controller;

import static com.eproject.learning.controller.CtrlAbs.ERROR;
import static com.eproject.learning.controller.CtrlAbs.SUCCESS;
import com.eproject.learning.entity.FAQ;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author luan
 */
public class CtrlFAQ extends CtrlAbs{
    public static List<FAQ> getAllFAQ(){
        Session session=HibernateUtil.getSessionFactory().openSession();
        
        return (List<FAQ>)session.createCriteria(FAQ.class)
                .list();
    }
    public static FAQ getById(int ID){
        Session session=HibernateUtil.getSessionFactory().openSession();
        FAQ f=(FAQ)session.get(FAQ.class, ID);
        return f;
    }
    public static String add(FAQ fAQ){
        Session session=null;
        
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            session.save(fAQ);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            System.out.println("Add FAQ error !");
            ex.printStackTrace();
            session.close();
            return ERROR;
        }
        return SUCCESS;
        
    }
    public static String update(FAQ fAQ){
        Session session=null;
        
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            session.merge(fAQ);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            System.out.println("Update Course error !");
            ex.printStackTrace();
            session.close();
            return ERROR;
        }
        return SUCCESS;
    }
     public static String delete(Integer fAQId){
        Session session=null;
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            FAQ fAQ=(FAQ) session.get(FAQ.class, fAQId);
            session.delete(fAQ);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            ex.printStackTrace();
            System.out.println("Delete FAQ error !");
            return ERROR;
        }
         System.out.println("Deleted");
        return SUCCESS;
    }
}
