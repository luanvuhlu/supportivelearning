/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.authentication;

import com.eproject.learning.controller.CtrlAbs;
import com.eproject.learning.controller.CtrlAccount;
import com.eproject.learning.entity.Student;
import com.eproject.learning.entity.User;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author luan
 */
@ManagedBean
@SessionScoped
public class Authentication implements Serializable{
    public static final int USER=0;
    public static final int STUDENT=2;
    
    public static final int ROLE_STUDENT=0;
    public static final int ROLE_STAFF=1;
    public static final int ROLE_ADMIN=2;
    public static final int ROLE_ALL=3;
    public static final int ROLE_ADMIN_ROLE=4;
    
    public static final String USER_SESSION_KEY="user";
    public static final String STUDENT_SESSION_KEY="student";
    public static final String ROLE_SESSION_KEY="role";
    
    public Object getAccount(){
        FacesContext fContext=FacesContext.getCurrentInstance();
        HttpSession session=(HttpSession)fContext.getExternalContext().getSession(true);
        if(session.getAttribute(USER_SESSION_KEY) !=null && session.getAttribute(USER_SESSION_KEY).toString() !=""){
            User user=(User)CtrlAccount.findById(Integer.parseInt(session.getAttribute(USER_SESSION_KEY).toString()), CtrlAccount.USER);
            return user;
        }
        if(session.getAttribute(STUDENT_SESSION_KEY) !=null && session.getAttribute(STUDENT_SESSION_KEY).toString() !=""){
            Student student=(Student)CtrlAccount.findById(Integer.parseInt(session.getAttribute(STUDENT_SESSION_KEY).toString()), CtrlAccount.STUDENT);
            if(student.getFirstName()==null || student.getLastName()==null){
                return "Unknown";
            }
            return student;
        }
        return null;
    }
    public static User getUser(){
        FacesContext fContext=FacesContext.getCurrentInstance();
        HttpSession session=(HttpSession)fContext.getExternalContext().getSession(true);
         if(session.getAttribute(USER_SESSION_KEY) !=null && session.getAttribute(USER_SESSION_KEY).toString() !=""){
            User user=(User)CtrlAccount.findById(Integer.parseInt(session.getAttribute(USER_SESSION_KEY).toString()), CtrlAccount.USER);
            return user;
        }
         return null;
    }
    public static Student getStudent(){
        FacesContext fContext=FacesContext.getCurrentInstance();
        HttpSession session=(HttpSession)fContext.getExternalContext().getSession(true);
        if(session.getAttribute(STUDENT_SESSION_KEY) !=null && !session.getAttribute(STUDENT_SESSION_KEY).toString().equals("")){
            Student student=(Student)CtrlAccount.findById(Integer.parseInt(session.getAttribute(STUDENT_SESSION_KEY).toString()), CtrlAccount.STUDENT);
            return student;
        }
        return null;
    }
    public static int getRole(){
        FacesContext fContext=FacesContext.getCurrentInstance();
        HttpSession session=(HttpSession)fContext.getExternalContext().getSession(true);
        if(session==null || (session.getAttribute(USER_SESSION_KEY)==null && session.getAttribute(STUDENT_SESSION_KEY)==null))
            return ROLE_ALL;
        if(session.getAttribute(STUDENT_SESSION_KEY) !=null && !session.getAttribute(STUDENT_SESSION_KEY).toString().equals(""))
            return ROLE_STUDENT;
        if(!session.getAttribute(USER_SESSION_KEY).toString().equals(""))
            if(!session.getAttribute(ROLE_SESSION_KEY).toString().equals(""))
                if(session.getAttribute(ROLE_SESSION_KEY).toString().equalsIgnoreCase("admin"))
                    return ROLE_ADMIN;
                else
                    return ROLE_STAFF;
        return ROLE_ALL;
    }
}
