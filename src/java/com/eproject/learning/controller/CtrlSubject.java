/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.controller;

import com.eproject.learning.entity.Semester;
import com.eproject.learning.entity.Subject;
import com.eproject.learning.entity.SubjectSemester;
import java.util.ArrayList;
import java.util.HashSet;
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
public class CtrlSubject extends CtrlAbs{
    public static final String UNIQUE_ERROR="unique_code";
    public static List<Subject> getBySemesterId(Semester semester){
        Session session=HibernateUtil.getSessionFactory().openSession();
        List<Subject> subjects=new ArrayList<>();
        List<SubjectSemester> ss=(List<SubjectSemester>)session.createCriteria(SubjectSemester.class)
                .add(Restrictions.eq("semester", semester))
                .list();
        for(SubjectSemester s:ss){
            subjects.add(s.getSubject());
        }
        return subjects;
    }
    public static Set<Subject> getSubjectsInSemester(Integer semesterID){
        Session session=HibernateUtil.getSessionFactory().openSession();
        Semester semester=null;
        Set<Subject> subjects=new HashSet<>();
        try{
            semester=(Semester) session.get(Semester.class, semesterID);
        }catch(NullPointerException ex){ 
            ex.printStackTrace();
            System.out.println("Semester is null !");
            return null;
        }
        for(SubjectSemester s:semester.getSubjectSemesters()){
            subjects.add(s.getPk().getSubject());
        }
        return subjects;
    }
    public static List<Subject> getAllSubject(){
        Session session=HibernateUtil.getSessionFactory().openSession();
        
        return (List<Subject>)session.createCriteria(Subject.class)
                .addOrder(Order.asc("code"))
                .addOrder(Order.asc("name"))
                .list();
    }
    public static Subject getById(int ID){
        Session session=HibernateUtil.getSessionFactory().openSession();
        return (Subject)session.createCriteria(Subject.class)
                .add(Restrictions.eq("subjectId", ID))
                .uniqueResult();
    }
    public static String add(Subject subject){
        Session session=null;
        
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            Subject r=(Subject)session.createCriteria(Subject.class)
                    .add(Restrictions.eq("code", subject.getCode()))
                    .uniqueResult();
            if(r != null){
                return UNIQUE_ERROR;
            }
            session.save(subject);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            System.out.println("Add Subject error !");
            ex.printStackTrace();
            session.close();
            return ERROR;
        }
        return SUCCESS;
        
    }
    public static String update(Subject subject){
        Session session=null;
        
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            Subject r=(Subject)session.createCriteria(Subject.class)
                    .add(Restrictions.eq("code", subject.getCode()))
                    .uniqueResult();
            if(r != null && r.getCode().equalsIgnoreCase(subject.getCode()) && r.getSubjectId()!=subject.getSubjectId()){
                System.out.println("Subject code must be unique !");
                return UNIQUE_ERROR;
            } 
            session.merge(subject);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            System.out.println("Update Subject error !");
            ex.printStackTrace();
            session.close();
            return ERROR;
        }
        return SUCCESS;
    }
    public static String delete(Integer subjectId){
        Session session=null;
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            Subject subject=(Subject) session.get(Subject.class, subjectId);
            session.delete(subject);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            ex.printStackTrace();
            System.out.println("Delete subject error !");
            return ERROR;
        }
         System.out.println("Deleted");
        return SUCCESS;
    }
}
