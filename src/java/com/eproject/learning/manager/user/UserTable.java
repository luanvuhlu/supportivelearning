/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eproject.learning.manager.user;

import com.eproject.learning.controller.CtrlBatch;
import com.eproject.learning.controller.CtrlRole;
import com.eproject.learning.controller.CtrlSubject;
import com.eproject.learning.manager.semester.*;
import com.eproject.learning.controller.CtrlUser;
import com.eproject.learning.entity.Batch;
import com.eproject.learning.entity.Course;
import com.eproject.learning.entity.Role;
import com.eproject.learning.entity.User;
import com.eproject.learning.entity.Subject;
import java.io.Serializable;
import java.util.ArrayList;
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
public class UserTable implements Serializable {

    private boolean disable = true;
    private User currentUser;
    private User[] selectedUsers;
    private UserDataModel mediumUsersModel;
    private List<User> users = CtrlUser.getAll();

    private boolean isStaff = false;
    private final String dialogViewUserPath = "user-detail.xhtml";
    
    private String firstName;
    private String lastName;
    private String username;
    private Role[] roles;
    private Subject subject;
    private boolean gender;
    private Batch batch;

    public UserTable() {
        mediumUsersModel = new UserDataModel(users);
        currentUser = new User();
    }
    
    public List<Role> getAllRoles(){
        return CtrlRole.getAllRole();
    }
    public List<Batch> getAllBatchs(){
        return CtrlBatch.getAll();
    }
    
    public void search(){
        
    }

    public String getRolesOfUser(User user) {
        Set<Role> roles = null;
        try {
            roles = user.getRoles();
        } catch (NullPointerException ex) {
            System.out.println("Roles of user are null !");
            return "";
        }
        String rolesStr = "  ";
        for (Role role : roles) {
            rolesStr += role.getName()+", ";
        }
        return rolesStr.substring(0, rolesStr.length()-2);
    }

    
    public void delete() {
        String result = "";
        List<User> failLs = new ArrayList<>();
        FacesMessage message = null;
        for (User user : selectedUsers) {
            if(user.getBatchs() !=null && user.getBatchs().size() >0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete error", "User: "+user.getFullName()+" has some batchs.Please delete batchs before !");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                return;
            }
            result = CtrlUser.delete(user.getUserId());
            if (result.equals(CtrlUser.ERROR)) {
                failLs.add(user);
            }
        }
        if (failLs.size() > 0) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete error", failLs.toString() + " are'nt deleted.\nPlease try again !");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Infomation", "Delete success");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        updateUserList();
    }
    private void updateUserList() {
        mediumUsersModel = new UserDataModel(CtrlUser.getAll());
    }

    public void select() {
        if (selectedUsers == null || selectedUsers.length < 1) {
            disable = true;
        } else {
            disable = false;
        }
    }

    public void changeStatus() {
        String result = "";
        List<User> failLs = new ArrayList<>();
        FacesMessage message = null;
        for (User user : selectedUsers) {
            user.setStatus(!user.isStatus());
            result = CtrlUser.update(user);
            if (result.equals(CtrlUser.ERROR)) {
                failLs.add(user);
            }
        }
        if (failLs.size() > 0) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Change status error", failLs.toString() + " can't change.\nPlease try again !");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Change status Infomation", "Change status success");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        updateUserList();
    }

    public void showUserDetail(User user) {
        currentUser = user;
        isStaff = false;
        for (Role role : currentUser.getRoles()) {
            if (role.getName().equals("staff")) {
                isStaff = true;
            }
        }
    }

  

    public User[] getUsersHaveRole(Integer roleId) {
        Set<User> us = CtrlUser.getUsersHaveRole(roleId);
        if (us == null) {
            System.out.println("Role is null......");
            return null;
        }
        User[] users = us.toArray(new User[us.size()]);
        return users;
    }

    public String getBatchsOfStaff(User user) {
        if (user == null) {
            return "";
        }
        Set<Batch> batchs = user.getBatchs();
        String batchsStr = "  ";
        if (batchs == null) {
            return batchsStr;
        }
        for (Batch b : batchs) {
            batchsStr += b.getName() + ", ";
        }
        return batchsStr.substring(0, batchsStr.length()-2);
    }

    public String getSubjectsOfStaff(User user) {
        if (user == null) {
            return "";
        }
        Set<Subject> subjects = user.getSubjects();
        String subjectsStr = "  ";
        if (subjects == null) {
            return subjectsStr;
        }
        for (Subject s : subjects) {
            subjectsStr += s.getCode()+ ", ";
        }
        return subjectsStr.substring(0, subjectsStr.length()-2);
    }

    // Don't delete above
    public UserDataModel getMediumUsersModel() {
        return mediumUsersModel;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setMediumUsersModel(UserDataModel mediumUsersModel) {
        this.mediumUsersModel = mediumUsersModel;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


    public String getDialogViewUserPath() {
        return dialogViewUserPath;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setSelectedUsers(User[] selectedUsers) {
        this.selectedUsers = selectedUsers;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public User[] getSelectedUsers() {
        return selectedUsers;
    }

  

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

   

    public boolean isIsStaff() {
        return isStaff;
    }

    public void setIsStaff(boolean isStaff) {
        this.isStaff = isStaff;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role[] getRoles() {
        return roles;
    }

    public void setRoles(Role[] roles) {
        this.roles = roles;
    }

    

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }
    

}
