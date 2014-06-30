/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.controller;

import static com.eproject.learning.controller.CtrlAbs.ERROR;
import static com.eproject.learning.controller.CtrlAbs.SUCCESS;
import com.eproject.learning.entity.Role;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author luan
 */
public class CtrlRole extends CtrlAbs{
    public static final String UNIQUE_ERROR="unique_name";
    public static List<Role> getAllRole(){
        Session session=HibernateUtil.getSessionFactory().openSession();
        return (List<Role>)session.createCriteria(Role.class)
                .addOrder(Order.asc("name"))
                .list();
    }
    public static Role getRoleById(Integer id){
        Session session=HibernateUtil.getSessionFactory().openSession();
        return (Role)session.createCriteria(Role.class)
                .add(Restrictions.eq("roleId", id))
                .uniqueResult();
    }
    public static String add(Role role){
        Session session=null;
        
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            Role r=(Role)session.createCriteria(Role.class)
                    .add(Restrictions.eq("name", role.getName()))
                    .uniqueResult();
            if(r != null){
                return UNIQUE_ERROR;
            }
            session.save(role);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            System.out.println("Add Role error !");
            ex.printStackTrace();
            session.close();
            return ERROR;
        }
        return SUCCESS;
        
    }
    public static String update(Role role){
        Session session=null;
        
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            Role r=(Role)session.createCriteria(Role.class)
                    .add(Restrictions.eq("name", role.getName()))
                    .uniqueResult();
            if(r != null && r.getName().equalsIgnoreCase(role.getName()) && r.getRoleId()!=role.getRoleId()){
                System.out.println("Role name must be unique !");
                return UNIQUE_ERROR;
            } 
            session.merge(role);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            System.out.println("Update Role error !");
            ex.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }
    public static String delete(Integer roleId){
        Session session=null;
        Transaction tran=null;
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            tran=session.beginTransaction();
            Role role=(Role) session.get(Role.class, roleId);
            session.delete(role);
            tran.commit();
        }catch(Exception ex){
            if(tran !=null && tran.isActive())
                tran.rollback();
            ex.printStackTrace();
            System.out.println("Delete role error !");
            return ERROR;
        }
         System.out.println("Deleted");
        return SUCCESS;
    }
    
}
