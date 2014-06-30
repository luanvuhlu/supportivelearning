/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.student;

import com.eproject.learning.controller.CtrlBatch;
import com.eproject.learning.manager.student.*;
import com.eproject.learning.controller.CtrlStudent;
import com.eproject.learning.entity.Batch;
import com.eproject.learning.entity.Student;
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
public class StudentTable implements Serializable{
     private boolean disable = true;
    private Student[] selectedStudents;
    private StudentDataModel mediumStudentsModel;
    private List<Student> students=CtrlStudent.getAll();
    
//    private final String dialogAddStudentPath="add-student.xhtml";
//    private final String dialogViewStudentPath="student-detail.xhtml";
//    private final String dialogEditStudentPath="edit-student.xhtml";
//    private final String dialogSelectBatchPath="select-batch.xhtml";

    public StudentTable() {
        
        mediumStudentsModel=new StudentDataModel(students);
    }
    
   public void delete() {
        String result = "";
        List<Student> failLs = new ArrayList<>();
        FacesMessage message = null;
        for (Student student : selectedStudents) {
            result = CtrlStudent.delete(student.getStudentId());
            if (result.equals(CtrlStudent.ERROR)) {
                failLs.add(student);
            }
        }
        if (failLs.size() > 0) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete error", failLs.toString() + " are'nt deleted.Please try again !");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Infomation", "Delete success");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        updateStudentList();
    }
   private void updateStudentList() {
        mediumStudentsModel = new StudentDataModel(CtrlStudent.getAll());
    }

    public void select() {
        if (selectedStudents == null || selectedStudents.length < 1) {
            disable = true;
        } else {
            disable = false;
        }
    }
     public void changeStatus() {
         String result = "";
        List<Student> failLs = new ArrayList<>();
        FacesMessage message = null;
        for (Student student : selectedStudents) {
            student.setStatus(!student.isStatus());
            result = CtrlStudent.update(student);
            if (result.equals(CtrlStudent.ERROR)) {
                failLs.add(student);
            }
        }
        if (failLs.size() > 0) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Change status error", failLs.toString() + " can't change.\nPlease try again !");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Change status Infomation", "Change status success");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        updateStudentList();
    }

   
    // Don't delete above
    public StudentDataModel getMediumStudentsModel() {
        return mediumStudentsModel;
    }

   

    public List<Student> getStudents() {
        return students;
    }

    public void setMediumStudentsModel(StudentDataModel mediumStudentsModel) {
        this.mediumStudentsModel = mediumStudentsModel;
    }

    

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setSelectedStudents(Student[] selectedStudents) {
        this.selectedStudents = selectedStudents;
    }

//    public void setSelectedStudents(List<Student> selectedStudents) {
//        this.selectedStudents = selectedStudents;
//    }
//
//    public List<Student> getSelectedStudents() {
//        return selectedStudents;
//    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

   
//
//    public String getDialogAddStudentPath() {
//        return dialogAddStudentPath;
//    }
//
//    public String getDialogEditStudentPath() {
//        return dialogEditStudentPath;
//    }
//
//    public String getDialogViewStudentPath() {
//        return dialogViewStudentPath;
//    }
//
//   
//    public String getDialogSelectBatchPath() {
//        return dialogSelectBatchPath;
//    }
//
//   

    public Student[] getSelectedStudents() {
        return selectedStudents;
    }
}
