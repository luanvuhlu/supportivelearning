/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.controller;


import com.eproject.learning.entity.Role;
import com.eproject.learning.entity.Student;
import com.eproject.learning.entity.Student;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions ;

/**
 *
 * @author luan
 */

public class CtrlStudent extends CtrlAbs implements Serializable{
    public static List<Student> getAll(){  
        Session session=HibernateUtil.getSessionFactory().openSession();
        List<Student> students=(List<Student>)session.createCriteria(Student.class)
                .addOrder(Order.asc("roll"))
                .addOrder(Order.asc("firstName"))
                .addOrder(Order.desc("dateCreate"))
                .addOrder(Order.asc("status"))
                .list();
        return students;
    }
    public static Student getById(int ID){ 
        Session session=HibernateUtil.getSessionFactory().openSession();
        Student s=(Student)session.get(Student.class, ID);
        return s;
    }
    public static String delete(Integer studentId){
        Session session=null;
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            Student student=(Student) session.get(Student.class, studentId);
            session.delete(student);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            ex.printStackTrace();
            System.out.println("Delete student error !");
            session.close();
            return ERROR;
        }
        return SUCCESS;
    }
    public static String add(Student student){
        Session session=null;
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            session.save(student);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            ex.printStackTrace();
            System.out.println("Add student error !");
            session.close();
            return ERROR; 
        }
        return SUCCESS;
    }
    public static String update(Student student){
        Session session=null;
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            session.merge(student);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            ex.printStackTrace();
            System.out.println("Update student error !");
            return ERROR;
        }
        return SUCCESS;
    }
    
}
