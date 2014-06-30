/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.studentassignment;


import com.eproject.learning.controller.CtrlAssignment;
import com.eproject.learning.controller.CtrlCourse;
import com.eproject.learning.controller.CtrlSemester;
import com.eproject.learning.controller.CtrlSubject;
import com.eproject.learning.controller.CtrlUser;
import com.eproject.learning.entity.Assignment;
import com.eproject.learning.entity.Course;
import com.eproject.learning.entity.Semester;
import com.eproject.learning.entity.Subject;
import com.eproject.learning.entity.User;
import com.eproject.learning.manager.assignment.AssignmentDataModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author luan
 */
@ManagedBean
@ViewScoped
@SessionScoped
public class StudentAssignmentTable implements Serializable{
    private List<Assignment> assignments=CtrlAssignment.getAllAssignment();
    private Assignment currentAssignment;
    private Assignment newAssignment;
    private Assignment editAssignment;
    private Assignment[] selectedAssignments;
    private AssignmentDataModel mediumAssignmentsModel;
    private final String dialogAddPath="add-assignment.xhtml";
    private final String dialogViewPath="assignment-detail.xhtml";
    private final String dialogEditPath="edit-assignment.xhtml";

    public StudentAssignmentTable() {
        mediumAssignmentsModel=new AssignmentDataModel(assignments);
        newAssignment=new Assignment();
        currentAssignment=new Assignment();
        editAssignment=currentAssignment;
    }
    
    
    public void showDetail(Assignment assignment){
        currentAssignment=assignment;
    }
    public void deleteAssignment(Assignment assignment){
        
    }
    public void updateOrAdd(Assignment assignment){
        System.out.println("Update"); 
        String msg = "";
        String title="";
        FacesMessage fMsg = null ;
        String result="";
        System.out.println("Save............");
        if(assignment.getAssignmentId()==0){
            title="Add Assignment";
            result=CtrlAssignment.add(assignment);
            
        }else{
            title="Update Assignment";
            result=CtrlAssignment.update(assignment);
        }
        if (result == CtrlAssignment.ERROR) {
            msg = "Error";
            fMsg = new FacesMessage(title, msg);
        } else {
            msg = "Success";
            fMsg = new FacesMessage(title, msg);
        }
        FacesContext.getCurrentInstance().addMessage(null, fMsg);
        newAssignment=new Assignment();
        editAssignment=new Assignment();
    }
    public void showAddDialog(){
        newAssignment=new Assignment();
    }
    public void showEdit(Assignment assignment){
        editAssignment=assignment;
    }
    public void closeDialog(){
        
    }
    
    public List<Course> getCourses(){
        return CtrlCourse.getAllCourse();
    }
  
   
    public List<Assignment> getAssignments() {
        return assignments;
    }
    

    public AssignmentDataModel getMediumAssignmentsModel() {
        return mediumAssignmentsModel;
    }

   

    public Assignment[] getSelectedAssignments() {
        return selectedAssignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public void setMediumAssignmentsModel(AssignmentDataModel mediumAssignmentsModel) {
        this.mediumAssignmentsModel = mediumAssignmentsModel;
    }

   

    public void setSelectedAssignments(Assignment[] selectedAssignments) {
        this.selectedAssignments = selectedAssignments;
    }

    public Assignment getCurrentAssignment() {
        return currentAssignment;
    }

    public Assignment getEditAssignment() {
        return editAssignment;
    }

    public Assignment getNewAssignment() {
        return newAssignment;
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
    
    

    
}
