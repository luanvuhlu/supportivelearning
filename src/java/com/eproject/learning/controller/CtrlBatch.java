/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.controller;

import com.eproject.learning.entity.Batch;
import com.eproject.learning.entity.Course;
import com.eproject.learning.entity.Semester;
import com.eproject.learning.entity.User;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions; 

/** 
 *
 * @author luan
 */
public class CtrlBatch extends CtrlAbs{
    public static List<Batch> getAll(){
        Session session=HibernateUtil.getSessionFactory().openSession();
        return (List<Batch>)session.createCriteria(Batch.class)
                .addOrder(Order.desc("startDate"))
                .list(); 
    }
    public static Set<Batch> getBatchsInCourse(int courseId){
        Session session=HibernateUtil.getSessionFactory().openSession();
        Course c=(Course)session.createCriteria(Course.class)
                .add(Restrictions.eq("courseId", courseId))
                .uniqueResult();
        return c.getBatchs();
    }
    public static Batch getBatchById(int ID){
        Session session=HibernateUtil.getSessionFactory().openSession();
        Batch b=(Batch)session.get(Batch.class, ID);
        return b;
    }
    public static Batch getByName(String name){
        Session session=HibernateUtil.getSessionFactory().openSession();
        Batch b=(Batch)session.createCriteria(Batch.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
        return b;
    }
    public static String delete(Integer batchId){
        Session session=null;
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            Batch batch=(Batch) session.get(Batch.class, batchId);
            session.delete(batch);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            ex.printStackTrace();
            System.out.println("Delete batch error !");
            return ERROR;
        }
        return SUCCESS;
    }
    public static String add(Batch batch){
        Session session=null;
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            session.save(batch);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            ex.printStackTrace();
            System.out.println("Add batch error !");
            return ERROR;
        }
        return SUCCESS;
    }
    public static String update(Batch batch){
        Session session=null;
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            session.merge(batch);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            ex.printStackTrace();
            System.out.println("Update batch error !");
            return ERROR;
        }
        return SUCCESS;
    }
    
}
