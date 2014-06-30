/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.controller;


import com.eproject.learning.entity.Role;
import com.eproject.learning.entity.Student;
import com.eproject.learning.entity.Subject;
import com.eproject.learning.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author luan
 */
public class CtrlUser extends CtrlAbs{
    public static List<User> getAll(){  
        Session session=HibernateUtil.getSessionFactory().openSession();
        List<User> users=(List<User>)session.createCriteria(User.class).list();
        return users;
    }
    public static User getById(int ID){
        Session session=HibernateUtil.getSessionFactory().openSession();
        return (User)session.createCriteria(User.class)
                .add(Restrictions.eq("userId", ID))
                .uniqueResult();
    }
    public static Set<User> getUsersHaveRole(Integer roleId){
        Session session=HibernateUtil.getSessionFactory().openSession();
        Role role=(Role)session.get(Role.class, roleId);
        if(role==null ){
            System.out.println("Role is null...........");
            return null;
        }
        return role.getUsers();
    }
    public static List<User> getUsersHaveSubject(Integer subjectId){
        Session session=HibernateUtil.getSessionFactory().openSession();
        List<User> us=new ArrayList<>();
        Subject s=(Subject)session.get(Subject.class, subjectId);
        if(s==null){
            System.out.println("Subject "+subjectId+ " is null");
            return us;
        }
        return new Convert<User>().setToList(s.getUsers());
    }
    public static String delete(Integer userId){
        Session session=null;
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            User user=(User) session.get(User.class, userId);
            session.delete(user);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            ex.printStackTrace();
            System.out.println("Delete user error !");
            return ERROR;
        }
        return SUCCESS;
    }
    public static String add(User user){
        Session session=null;
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            session.save(user);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            ex.printStackTrace();
            System.out.println("Add user error !");
            return ERROR;
        }
        return SUCCESS;
    }
    public static String update(User user){
        Session session=null;
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            boolean has=false;
            for(Role r:user.getRoles()){
                if(r.getName().equalsIgnoreCase("staff")){
                    has=true;
                    break;
                }
                    
            }
            if(!has){
               user.setSubjects(null);
            }
             
            session.merge(user);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            ex.printStackTrace();
            System.out.println("Update user error !");
            return ERROR;
        }
        return SUCCESS;
    }
    public static boolean isAdmin(Integer userId){
        Session session=null;
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            User user=(User) session.get(User.class, userId);
            for(Role r:user.getRoles()){
                if(r.getName().equalsIgnoreCase("admin")){
                    return true;
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Check Admin error !");
            return false;
        }
        return false;
    }
    public static boolean isStaff(Integer userId){
        Session session=null;
        
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            
            User user=(User) session.get(User.class, userId);
            for(Role r:user.getRoles()){
                if(r.getName().equalsIgnoreCase("staff")){
                    return true;
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Check Staff error !");
            return false;
        }
        return false;
    }
    
}
