/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.profile;

import com.eproject.learning.authentication.Authentication;
import com.eproject.learning.entity.Student;
import com.eproject.learning.entity.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author luan
 */
@ManagedBean
@ViewScoped
@SessionScoped
public class ProfileBean {
    private int role;
    public static final int ROLE_STUDENT=0;
    public static final int ROLE_STAFF=1;
    public static final int ROLE_ADMIN=2;
    private Student student;
    private User user;
    private void checkRole(){
        role=Authentication.getRole();
        if(role==ROLE_STUDENT){
            student=Authentication.getStudent();
        }else if(role==ROLE_STAFF || role==ROLE_ADMIN){
            user=Authentication.getUser();
        }
    }

    public static int getROLE_ADMIN() {
        return ROLE_ADMIN;
    }

    public static int getROLE_STAFF() {
        return ROLE_STAFF;
    }

    public static int getROLE_STUDENT() {
        return ROLE_STUDENT;
    }

    public int getRole() {
        return role;
    }

    public Student getStudent() {
        return student;
    }

    public User getUser() {
        return user;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
