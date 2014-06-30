/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eproject.learning.manager.role;

import com.eproject.learning.manager.role.*;
import com.eproject.learning.manager.role.*;
import com.eproject.learning.manager.faq.*;
import com.eproject.learning.controller.CtrlRole;
import com.eproject.learning.entity.Role;
import com.eproject.learning.manager.role.RoleDataModel;
import static com.sun.faces.facelets.util.Path.context;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author luan
 */
@ManagedBean
@ViewScoped
@SessionScoped
public class RoleTable implements Serializable {

    private List<Role> roles = CtrlRole.getAllRole();
//    private Role currentRole;
    private Role newRole;
    private Role editRole;
    private boolean disable = true;
    private Role[] selectedRoles;
    private RoleDataModel mediumRolesModel;
    private final String dialogAddPath = "add-role.xhtml";
    private final String dialogEditPath = "edit-role.xhtml";

    public RoleTable() {
        mediumRolesModel = new RoleDataModel(roles);
        newRole = new Role();
//        currentRole = new Role();
        editRole = new Role();
    }
//
//    public void showDetail(Role role) {
//        currentRole = role;
//    }

    private void updateRoleList() {
        mediumRolesModel = new RoleDataModel(CtrlRole.getAllRole());
        newRole = new Role();
//        currentRole = new Role();
    }

    public void delete() {
        String result="";
        List<Role> failLs=new ArrayList<>();
        FacesMessage message=null;
        for (Role role : selectedRoles) {
            role = CtrlRole.getRoleById(role.getRoleId());
            if (role.getUsers() != null && role.getUsers().size() > 0) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete error", role.getName() + " has some users. Please delete users before");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                return;
            }
            result=CtrlRole.delete(role.getRoleId());
            if(result.equals(CtrlRole.ERROR)){
                failLs.add(role);
            }
        }
        if(failLs.size() >0){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete error", failLs.toString()+" are'nt deleted.\nPlease try again !");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }else{
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Infomation", "Delete success");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
        updateRoleList();
    }

    public void select() {
        if(selectedRoles==null || selectedRoles.length <1)
            disable = true;
        else
            disable=false;
    }

    public void updateOrAdd(Role role) {
        boolean success = false;
        RequestContext context = RequestContext.getCurrentInstance();
        String msg = "";
        String title = "";
        FacesMessage fMsg = null;
        String result = "";
        if (role == null) {
            msg = "Error";
            fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        } else if (role.getName().trim().equals("")) {
            msg = "Name is required !";
            fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        } else {
            if (role.getRoleId() == 0) {
                title = "Add Role";
                result = CtrlRole.add(role);
            } else {
                title = "Edit Role";
                result = CtrlRole.update(role);
            }
            if (result.equals(CtrlRole.SUCCESS)) {
                msg = "Success";
                fMsg = new FacesMessage(title, msg);
                success = true;
            } else {
                if (result.equals(CtrlRole.UNIQUE_ERROR)) {
                    msg = "Name must be unique !";
                    fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
                } else if (result.equals(CtrlRole.ERROR)) {
                    msg = "Error";
                    fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
                }
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, fMsg);
        context.addCallbackParam("update", success);
        reset(role);
        updateRoleList();
    }

    private void reset(Role role) {
        if (role.getRoleId() == 0) {
            role = new Role();
        } else {
            role = CtrlRole.getRoleById(role.getRoleId());
        }
    }

    public void showAddDialog() {
        newRole = new Role();
    }

    public void showEdit(Role role) {
        editRole = role;
    }

    public void closeDialog() {

    }

//    public Role getCurrentRole() {
//        return currentRole;
//    }
    public String getDialogAddPath() {
        return dialogAddPath;
    }

    public String getDialogEditPath() {
        return dialogEditPath;
    }

//    public String getDialogViewPath() {
//        return dialogViewPath;
//    }
    public Role getEditRole() {
        return editRole;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public RoleDataModel getMediumRolesModel() {
        return mediumRolesModel;
    }

    public Role getNewRole() {
        return newRole;
    }

    public Role[] getSelectedRoles() {
        return selectedRoles;
    }

//    public void setCurrentRole(Role currentRole) {
//        this.currentRole = currentRole;
//    }
    public void setEditRole(Role editRole) {
        this.editRole = editRole;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setMediumRolesModel(RoleDataModel mediumRolesModel) {
        this.mediumRolesModel = mediumRolesModel;
    }

    public void setNewRole(Role newRole) {
        this.newRole = newRole;
    }

    public void setSelectedRoles(Role[] selectedRoles) {
        this.selectedRoles = selectedRoles;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

}
