/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.controller;

import com.eproject.learning.entity.Course;
import com.eproject.learning.entity.Semester;
import com.eproject.learning.entity.SubjectSemester;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author luan
 */
public class CtrlSemester extends CtrlAbs{
    public static Semester findByOrder(Integer order, Course course){
        Session session=HibernateUtil.getSessionFactory().openSession();
        return (Semester)session.createCriteria(Semester.class)
                .add(Restrictions.eq("course", course))
                .add(Restrictions.eq("semesterOrder", order))
                .uniqueResult();
        
    }
    public static Semester getSemesterById(int ID){
        Session session=HibernateUtil.getSessionFactory().openSession();
        return (Semester)session.createCriteria(Semester.class)
                .add(Restrictions.eq("semesterId", ID))
                .uniqueResult();
    }
    public static List<Semester> getAllSemesters(){
        Session session=HibernateUtil.getSessionFactory().openSession();
        return (List<Semester>)session.createCriteria(Semester.class)
                .list();
    }
    public static List<SubjectSemester> getSubjectSemester(Integer semesterId){
        Semester s=getSemesterById(semesterId);
        return new Convert().setToList(s.getSubjectSemesters());
        
    }
    public static String update(Semester newSemester){
        if(newSemester==null)
           return ERROR;
        Session session=null;
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            session.merge(newSemester);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            ex.printStackTrace();
            System.out.println("Update Semester Error");
            session.close();
            return ERROR;
        }
        return SUCCESS;
    }
    
    public static String saveOrUpdateSubject(SubjectSemester subjectSemester){
        if(subjectSemester==null)
           return ERROR;
        Session session=null;
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            session.saveOrUpdate(subjectSemester);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            ex.printStackTrace();
            System.out.println("Update Subject in Semester Error");
            session.close();
            return ERROR;
        }
        return SUCCESS;
    }
    
    public static String delete(SubjectSemester subjectSemester){
        if(subjectSemester==null)
           return ERROR;
        Session session=null;
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            session.delete(subjectSemester);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            ex.printStackTrace();
            System.out.println("Delete Subject in Semester Error");
            session.close();
            return ERROR;
        }
        return SUCCESS;
    }
    
    public static String delete(Integer semesterId){
        Session session=null;
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            Semester semester= (Semester) session.get(Semester.class, semesterId);
            if(semester==null){
                System.out.println("Semester is null");
                return ERROR;
            }
            session.delete(semester);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            ex.printStackTrace();
            System.out.println("Delete Semester Error");
            session.close();
            return ERROR;
        }
        return SUCCESS;
    }
    
     public static String add(Semester newSemester){
        if(newSemester==null)
           return ERROR;
        Session session=null;
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            session.merge(newSemester);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            ex.printStackTrace();
            System.out.println("Add Semester Error");
            session.close();
            return ERROR;
        }
        return SUCCESS;
    }
     public static List<Semester> sortSemester(List<Semester> semesters) {
        if (semesters == null) {
            return semesters;
        }
        Collections.sort(semesters, new Comparator<Semester>() {
            @Override
            public int compare(Semester s1, Semester s2) {
                return s1.getSemesterOrder().compareTo(s2.getSemesterOrder());
            }
        });
        return semesters;
    }
     public static List<SubjectSemester> sortSubjectSemester(List<SubjectSemester> ss) {
        if (ss == null) {
            return ss;
        }
        Collections.sort(ss, new Comparator<SubjectSemester>() {
            @Override
            public int compare(SubjectSemester s1, SubjectSemester s2) {
                if(s1.getSubjectOrder() ==null && s2.getSubjectOrder() !=null){
                    return -1;
                }
                if(s1.getSubjectOrder() !=null && s2.getSubjectOrder() ==null){
                    return 1;
                }
                if(s1.getSubjectOrder()==null && s2.getSubjectOrder()==null){
                    return 1;
                }
                return s1.getSubjectOrder().compareTo(s2.getSubjectOrder());
            }
        });
        return ss;
     }

}
