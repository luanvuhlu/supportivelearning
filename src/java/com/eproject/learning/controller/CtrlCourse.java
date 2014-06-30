/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.controller;

import com.eproject.learning.entity.Course;
import com.eproject.learning.entity.Semester;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author luan
 */
public class CtrlCourse extends CtrlAbs{
    public static List<Course> getAllCourse(){
        Session session=HibernateUtil.getSessionFactory().openSession();
        
        return (List<Course>)session.createCriteria(Course.class)
                .addOrder(Order.asc("code"))
                .list();
    }    
    public static Course getById(int ID){
        Session session=HibernateUtil.getSessionFactory().openSession();
        return (Course)session.get(Course.class, ID);
    }
    public static Course getByCode(String code){
        Session session=HibernateUtil.getSessionFactory().openSession();
        return (Course)session.createCriteria(Course.class)
                .add(Restrictions.eq("code", code))
                .uniqueResult();
    }
    public static String add(Course course){
        Session session=null;
        
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            session.save(course);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            System.out.println("Add Course error !");
            ex.printStackTrace();
            session.close();
            return ERROR;
        }
        return SUCCESS;
    }
    
    public static boolean isUnique(Course course){
        Session session=null;
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            Criteria criteria=session.createCriteria(Course.class);
            criteria=criteria.add(Restrictions.eq("code", course.getCode()))
                    .add(Restrictions.eq("name", course.getName()));
            Course oldCourse=(Course)criteria.uniqueResult();
            if(oldCourse ==null || oldCourse.getCourseId()==0)
                return true;
            if(oldCourse.getCourseId() == course.getCourseId())
                return true;
        }catch(Exception ex){
            System.out.println("Course Action error !");
            ex.printStackTrace();
            session.close();
            return false;
        }
        return false;
        
    }
    public static String update(Course course){
        Session session=null;
        
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            session.merge(course);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            System.out.println("Update Course error !");
            ex.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
        
    }
    public static String delete(int courseId){
        Session session=null;
        
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            if(courseId==0){
                return ERROR;
            }
            Course course=(Course)session.get(Course.class, courseId);
            if(course==null){
                return ERROR;
            }
            session.delete(course);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            System.out.println("Delete Course error !");
            ex.printStackTrace();
            session.close();
            return ERROR;
        }
        return SUCCESS;
    }
    public static String deleteSemesterInCourse(Integer courseId, Integer semesterId){
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction tran=null;
        try{
            tran=session.beginTransaction();
            Course course=(Course)session.get(Course.class, courseId);
            Semester semester=(Semester)session.get(Semester.class, semesterId);
            course.getSemesters().remove(semester);
            session.update(course);
        }catch(Exception ex){
            if(tran !=null && tran.isActive()){
                tran.rollback();
            }
            session.close();
            return ERROR;
        }
        return SUCCESS;
    }
    public static Set<Semester> getSemestersInCourse(Integer courseId){
        Session session=HibernateUtil.getSessionFactory().openSession();
        Course course=(Course)session.get(Course.class, courseId);
        return course.getSemesters();
    }
}
