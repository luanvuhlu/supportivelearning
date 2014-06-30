/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.subject;

import com.eproject.learning.authentication.Authentication;
import com.eproject.learning.manager.subject.*;
import com.eproject.learning.manager.faq.*;
import com.eproject.learning.controller.CtrlSubject;
import com.eproject.learning.entity.Subject;
import com.eproject.learning.entity.User;
import com.eproject.learning.manager.subject.SubjectDataModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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
public class SubjectTable implements Serializable{
    private List<Subject> subjects=CtrlSubject.getAllSubject();
    private Subject currentSubject;
    private Subject newSubject;
    private Subject editSubject;
    private boolean disable = true;
    private Subject[] selectedSubjects;
    private SubjectDataModel mediumSubjectsModel;
    private final String dialogAddPath="add-subject.xhtml";
    private final String dialogViewPath="subject-detail.xhtml";
    private final String dialogEditPath="edit-subject.xhtml";

    public SubjectTable() { 
        mediumSubjectsModel=new SubjectDataModel(subjects);
        newSubject=new Subject(); 
        currentSubject=new Subject();
        editSubject=currentSubject;
    }
    
   
    public void showDetail(Subject subject){
        currentSubject=subject;
    }
    private void updateSubjectList() {
        mediumSubjectsModel = new SubjectDataModel(CtrlSubject.getAllSubject());
        newSubject = new Subject();
        currentSubject = new Subject();
    }
    public boolean isStudying(Subject subject){
        User user=Authentication.getUser();
        if(user==null){
            return false;
        }
        for(Subject s:user.getSubjects()){
            if(subject.getSubjectId().equals(s.getSubjectId())){
                return true;
            }
        }
        return false;
    }
    public void delete(){
        String result="";
        List<Subject> failLs=new ArrayList<>();
        FacesMessage message=null;
        for (Subject subject : selectedSubjects) {
            subject = CtrlSubject.getById(subject.getSubjectId());
            if (subject.getUsers() != null && subject.getUsers().size() > 0) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete error", subject.getName() + " has some users. Please delete users before");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                return;
            }
            if (subject.getUsers() != null && subject.getBatchs().size() > 0) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete error", subject.getName() + " has some batchs. Please delete batchs before");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                return;
            }
            if (subject.getUsers() != null && subject.getAssignments().size() > 0) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete error", subject.getName() + " has some assignments. Please delete assignments before");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                return;
            }
            result=CtrlSubject.delete(subject.getSubjectId());
            if(result.equals(CtrlSubject.ERROR)){
                failLs.add(subject);
            }
        }
        if(failLs.size() >0){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete error", failLs.toString()+" are'nt deleted.\nPlease try again !");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }else{
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Infomation", "Delete success");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
        updateSubjectList();
    }
    public void updateOrAdd(Subject subject){
        boolean success = false;
        RequestContext context = RequestContext.getCurrentInstance();
        String msg = "";
        String title = "";
        FacesMessage fMsg = null;
        String result = "";
        if (subject == null) {
            msg = "Error";
            fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        } else if (subject.getCode().trim().equals("")) {
            msg = "Code is required !";
            fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        } else if(subject.getName().trim().equals("")){
            msg = "Name is required !";
            fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        }else {
            if (subject.getSubjectId()==null || subject.getSubjectId() == 0) { 
                title = "Add Subject";
                result = CtrlSubject.add(subject);
            } else {
                title = "Edit Subject";
                result = CtrlSubject.update(subject);
            }
            if (result.equals(CtrlSubject.SUCCESS)) {
                msg = "Success";
                fMsg = new FacesMessage(title, msg);
                success = true;
            } else {
                if (result.equals(CtrlSubject.UNIQUE_ERROR)) {
                    msg = "Code must be unique !";
                    fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
                } else if (result.equals(CtrlSubject.ERROR)) {
                    msg = "Error";
                    fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
                }
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, fMsg);
        context.addCallbackParam("update", success);
        reset(subject);
        updateSubjectList();
    }
    private void reset(Subject subject) {
        if (subject==null || subject.getSubjectId()==null || subject.getSubjectId() == 0) {
            subject = new Subject();
        } else {
            subject = CtrlSubject.getById(subject.getSubjectId());
        }
    }
    public void select() {
        if(selectedSubjects==null || selectedSubjects.length <1)
            disable = true;
        else
            disable=false;  
    }
    public void showAddDialog(){
        newSubject=new Subject();
    }
    public void showEdit(Subject subject){
        editSubject=subject;
    }
    public Subject getCurrentSubject() {
        return currentSubject;
    }

    public String getDialogAddPath() {
        return dialogAddPath;
    }

    public String getDialogEditPath() {
        return dialogEditPath;
    }

    public String getDialogViewPath() {
        return dialogViewPath;
    }

    public Subject getEditSubject() {
        return editSubject;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public SubjectDataModel getMediumSubjectsModel() {
        return mediumSubjectsModel;
    }

    public Subject getNewSubject() {
        return newSubject;
    }

    public Subject[] getSelectedSubjects() {
        return selectedSubjects;
    }

    public void setCurrentSubject(Subject currentSubject) {
        this.currentSubject = currentSubject;
    }

    public void setEditSubject(Subject editSubject) {
        this.editSubject = editSubject;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void setMediumSubjectsModel(SubjectDataModel mediumSubjectsModel) {
        this.mediumSubjectsModel = mediumSubjectsModel;
    }

    public void setNewSubject(Subject newSubject) {
        this.newSubject = newSubject;
    }

    public void setSelectedSubjects(Subject[] selectedSubjects) {
        this.selectedSubjects = selectedSubjects;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public boolean isDisable() {
        return disable;
    }
    
  
}
