/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.controller;

import com.eproject.learning.entity.Assignment;
import com.eproject.learning.entity.Batch;
import com.eproject.learning.entity.Course;
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
public class CtrlAssignment extends CtrlAbs{
    public static List<Assignment> getAllAssignment(){
        Session session=HibernateUtil.getSessionFactory().openSession();
            List<Assignment> assignments=(List<Assignment>)session.createCriteria(Assignment.class)
                .addOrder(Order.asc("status"))
                    .addOrder(Order.asc("endDate"))
                .list(); 
            return assignments;
        
        
        
    }
    public static Assignment getById(int assignmentId){
        Session session=HibernateUtil.getSessionFactory().openSession();
            return (Assignment)session.get(Assignment.class, assignmentId);
    }
    public static List<Assignment> getAssignmentsOfBatch(Batch batch){
        Session session=HibernateUtil.getSessionFactory().openSession();
        return (List<Assignment>)session.createCriteria(Assignment.class)
                .add(Restrictions.eq("batch", batch))
                .list();  
    }
     public static String delete(Integer assignmentId){
        Session session=null;
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            Assignment assignment=(Assignment) session.get(Assignment.class, assignmentId);
            session.delete(assignment);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            ex.printStackTrace();
            System.out.println("Delete assignment error !");
            return ERROR;
        }
         System.out.println("Deleted");
        return SUCCESS;
    }
    public static String add(Assignment assignment){
        Session session=null;
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            session.save(assignment);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            ex.printStackTrace();
            System.out.println("Add assignment error !");
            return ERROR; 
        }
        return SUCCESS;
    }
    public static String update(Assignment assignment){
        Session session=null;
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            session.merge(assignment);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            ex.printStackTrace();
            System.out.println("Update assignment error !");
            return ERROR;
        }
        return SUCCESS;
    }
    
}
