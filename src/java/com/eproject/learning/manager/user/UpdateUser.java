/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eproject.learning.manager.user;

import com.eproject.learning.controller.CtrlRole;
import com.eproject.learning.controller.CtrlSubject;
import com.eproject.learning.manager.semester.*;
import com.eproject.learning.controller.CtrlUser;
import com.eproject.learning.entity.Batch;
import com.eproject.learning.entity.Course;
import com.eproject.learning.entity.Role;
import com.eproject.learning.entity.User;
import com.eproject.learning.entity.Subject;
import com.eproject.learning.view.ActionBean;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author luan
 */
@ManagedBean
@ViewScoped
@SessionScoped
public class UpdateUser implements Serializable {

    private User user;
    private boolean staff=false;
    private boolean addMode = false;
    private String subjectId;
    private String roleId;
    private String[] roleIds;
    private String[] subjectIds;

    public UpdateUser() {
        String userPara = ActionBean.getParameter("u");
        if (ActionBean.getParameter("a").equals("add")) {
            System.out.println("Add");
            addMode = true;
            user = new User();
        } else if (userPara.equalsIgnoreCase("")) {
            ActionBean.redirect("manager.xhtml?action=user");
            return;
        } else {
            user = CtrlUser.getById(Integer.parseInt(userPara));
            initSubjectAndRole();
            
        }
    }
    private void initSubjectAndRole(){
        
            int count=0;
            roleIds=new String[user.getRoles().size()];
            for(Role r:user.getRoles()){
                roleIds[count++]=r.getRoleId()+"";
            }
            if(checkIsStaff()){
                staff=true;
                System.out.println("Is Staff: "+staff+"...............");
            count=0;
            subjectIds=new String[user.getSubjects().size()];
            for(Subject s:user.getSubjects()){
                subjectIds[count++]=s.getSubjectId().toString();
            }
            }
    }

    public void save() {
        String msg = "";
        String title = "";
        FacesMessage fMsg = null;
        String result = "";
        if (user == null) {
            msg = "User is empty. Please try again !";
            fMsg = new FacesMessage(title, msg);
        } else {
            Set<Role> rs = new HashSet<>();
            for (String id : roleIds) {
                rs.add(CtrlRole.getRoleById(Integer.parseInt(id)));
            }
            user.setRoles(rs);
            if (subjectIds != null && subjectIds.length > 0) {
                Set<Subject> ss = new HashSet<>();
                for (String id : subjectIds) {
                    ss.add(CtrlSubject.getById(Integer.parseInt(id)));
                }
                user.setSubjects(ss);
            }
            if (user.getUserId() == null || user.getUserId().equals(0)) {
                title = "Add User";
                user.setDateCreate(new Date());
                user.setUsername(user.getFullName());
                user.setPassword("eproject");

                result = CtrlUser.add(user);
            } else {
                title = "Update User";
                result = CtrlUser.update(user);
            }
            if (result == CtrlUser.ERROR) {
                msg = "Error";
                fMsg = new FacesMessage(title, msg);
            } else {
                msg = "Success";
                fMsg = new FacesMessage(title, msg);
                reset();
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, fMsg);
    }

    public void reset() {
        if (addMode) {
            user = new User();
            return;
        }
        user = CtrlUser.getById(user.getUserId());
        initSubjectAndRole();

    }
    public void selectedRole(){
        staff=checkIsStaff();
    }
    public Map<String, Integer> getAllSubjects() {
        List<Subject> ss = CtrlSubject.getAllSubject();
        Map<String, Integer> map = new HashMap<>();
        if (!isStaff()) {
            return map;
        }
        for (Subject s : ss) {
            map.put(s.getCode(), s.getSubjectId());
        }
        return map;
    }

    public Map<String, Integer> getAllRoles() {
        List<Role> rs = CtrlRole.getAllRole();
        Map<String, Integer> map = new HashMap<>();
        for (Role r : rs) {
            map.put(r.getName(), r.getRoleId());
        }
        return map;
    }
    public boolean checkIsStaff(){
        if (roleIds == null) {
            return false;
        }
        Role r=null;
        for (String id : roleIds) {
             r= CtrlRole.getRoleById(Integer.parseInt(id));
            System.out.println(r.getName() + "..............");
            if (r != null && r.getName().equalsIgnoreCase("staff")) {
                return true;
            }
        }
        return false;
    }

    public boolean isStaff() {
        return staff;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public User getUser() {
        return user;
    }

    public void setAddMode(boolean addMode) {
        this.addMode = addMode;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
        if(checkIsStaff()){
            staff=true;
        }
    }

    public String[] getSubjectIds() {
        return subjectIds;
    }

    public void setSubjectIds(String[] subjectIds) {
        this.subjectIds = subjectIds;
    }

    public boolean isAddMode() {
        return addMode;
    }

    public void setStaff(boolean staff) {
        this.staff = staff;
    }
}
