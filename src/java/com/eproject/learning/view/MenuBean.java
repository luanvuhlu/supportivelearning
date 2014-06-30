/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.view;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author luan
 */
@ManagedBean
public class MenuBean {
    public static final int ROLE_STUDENT=0;
    public static final int ROLE_STAFF=1;
    public static final int ROLE_ADMIN=2;
    public static final int ROLE_ALL=3;
    public static final int ROLE_ADMIN_STAFF=4;
    
    public static final int MENU_TOP=0;
    public static final int MENU_LEFT=1;
    public static final int MENU_MANAGER=2;
    private String value;
    private String outcome;
    private int type;
    private int role;

    public MenuBean() {
    }

    public MenuBean(String value, String outcome, int type, int role) {
        this.value = value;
        this.outcome = outcome;
        this.type = type;
        this.role = role;
    }
    

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

}
