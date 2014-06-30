/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eproject.learning.controller;

import com.eproject.learning.entity.Student;
import com.eproject.learning.entity.User;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author luan
 */
public class CtrlAccount extends CtrlAbs {

    public static Object getAccount(String username, String password) {
        Object account = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        account = (User) session.createCriteria(User.class)
                .add(Restrictions.eq("username", username))
                .add(Restrictions.eq("password", password))
                .uniqueResult();
        if (account == null) {
            account = (Student) session.createCriteria(Student.class)
                    .add(Restrictions.eq("username", username))
                    .add(Restrictions.eq("password", password))
                    .uniqueResult();
        }
        return account;
    }

    public static Object findById(int ID, int type) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            if (type == USER) {
                return (User) session.createCriteria(User.class)
                        .add(Restrictions.eq("userId", ID)).uniqueResult();
            }
            if (type == STUDENT) {
                return (Student) session.createCriteria(Student.class)
                        .add(Restrictions.eq("studentId", ID)).uniqueResult();
            }

        } catch (HibernateException ex) {
            return null;
        }
        return null;
    }

    public static boolean isMultiRole(int id) {
        User user = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        user = (User) session.createCriteria(User.class)
                .add(Restrictions.eq("userId", id))
                .uniqueResult();
        if (user == null) {
            return false;
        }
        if (user.getRoles().size() > 1) {
            return true;
        }
        return false;
    }
}
