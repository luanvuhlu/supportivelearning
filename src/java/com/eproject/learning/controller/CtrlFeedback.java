/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.controller;

import static com.eproject.learning.controller.CtrlAbs.ERROR;
import static com.eproject.learning.controller.CtrlAbs.SUCCESS;
import com.eproject.learning.entity.Feedback;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

/**
 *
 * @author luan
 */
public class CtrlFeedback extends CtrlAbs{
    public static List<Feedback> getAllFeedback(){
        Session session=HibernateUtil.getSessionFactory().openSession();
        
        return (List<Feedback>)session.createCriteria(Feedback.class)
                .addOrder(Order.desc("date"))
                .list();
    }
    public static String add(Feedback feedback){
        Session session=null;
        
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            session.save(feedback);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            System.out.println("Add Feedback error !");
            ex.printStackTrace();
            session.close();
            return ERROR;
        }
        return SUCCESS;
        
    }
    public static String update(Feedback feedback){
        Session session=null;
        
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            session.merge(feedback);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            System.out.println("Update Feedback error !");
            ex.printStackTrace();
            session.close();
            return ERROR;
        }
        return SUCCESS;
    }
     public static String delete(Integer feedbackId){
        Session session=null;
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            Feedback feedback=(Feedback) session.get(Feedback.class, feedbackId);
            session.delete(feedback);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            ex.printStackTrace();
            System.out.println("Delete Feedback error !");
            return ERROR;
        }
         System.out.println("Deleted");
        return SUCCESS;
    }
}
