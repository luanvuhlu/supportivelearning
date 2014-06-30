/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eproject.learning.manager.assignment;

import com.eproject.learning.authentication.Authentication;
import com.eproject.learning.controller.CtrlAssignment;
import com.eproject.learning.controller.CtrlBatch;
import com.eproject.learning.controller.CtrlCourse;
import com.eproject.learning.controller.CtrlMark;
import com.eproject.learning.controller.CtrlSubject;
import com.eproject.learning.entity.Assignment;
import com.eproject.learning.entity.Batch;
import com.eproject.learning.entity.Course;
import com.eproject.learning.entity.MarkAssignment;
import com.eproject.learning.entity.Student;
import com.eproject.learning.entity.Subject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author luan
 */
@ManagedBean
@ViewScoped
public class AssignmentTable implements Serializable {
private boolean disable = true;
    private List<Assignment> assignments = CtrlAssignment.getAllAssignment();

    private UploadedFile file;

    private List<Batch> batchs;
    private List<Subject> subjects;

    private Assignment currentAssignment;
    private Assignment[] selectedAssignments;
    private AssignmentDataModel mediumAssignmentsModel;

    private final String dialogAddPath = "add-assignment.xhtml";
    private final String dialogViewPath = "assignment-detail.xhtml";
    private final String dialogEditPath = "edit-assignment.xhtml";
    private final String dialogSolutionPath = "solution-list.xhtml";

    public AssignmentTable() {
        mediumAssignmentsModel = new AssignmentDataModel(assignments);
        batchs = CtrlBatch.getAll();
        currentAssignment = new Assignment();
    }
    public void select() {
        if (selectedAssignments == null || selectedAssignments.length < 1) {
            disable = true;
        } else {
            disable = false;
        }
    }
    public void filUploadListener(FileUploadEvent event) {
        file = event.getFile();
 }
    public void uploadSolution() {
        Student student = Authentication.getStudent();
        if(student==null){
            System.out.println("Student is null");
            return;
        }
        FileUploadController.upload(file, student, currentAssignment);
        if (currentAssignment == null) {
            return;
        }
        CtrlMark.save(student, currentAssignment, file.getFileName());
    }
    public String hasSolution(Assignment assignment){
        if(assignment==null || assignment.getMarkAssignments() ==null){
            return 0+"";
        }
        return assignment.getMarkAssignments().size()+"";
    }

    public StreamedContent download(Assignment assignment) {
        return FileDownloadController.download(assignment);
    }
    public StreamedContent downloadSolution(MarkAssignment markAssignment) {
        
        return FileDownloadController.download(markAssignment);
    }

    public void selectedAssignment(Assignment assignment) {
        currentAssignment = assignment;
    }

    public List<Assignment> getAssignmentsOfBatch() {
        Student student = (Student) Authentication.getStudent();
        List<Assignment> assignments = null;
        if (student == null) {
            System.out.println("Student is null...........");
            return assignments;
        }
        System.out.println(student.getBatch().getName());
        try {
            assignments = CtrlAssignment.getAssignmentsOfBatch(student.getBatch());
        } catch (NullPointerException ex) {
            System.out.println("Assignment of Batch is null...........");
        }
        if (assignments == null) {
            System.out.println("Assignment is null...........");
            return new ArrayList<>();
        }
        return assignments;
    }

    public void showDetail(Assignment assignment) {
        currentAssignment = assignment;
    }

    public void delete() {
        for (Assignment a : selectedAssignments) {
            CtrlAssignment.delete(a.getAssignmentId());
        }
        mediumAssignmentsModel = new AssignmentDataModel(CtrlAssignment.getAllAssignment());
    }

    public MarkAssignment getSolution() {
        MarkAssignment markAssignment = CtrlMark.getMarkAssignment(Authentication.getStudent(), currentAssignment);
        return markAssignment;
    }

    public void changeStatus() {
        String result = "";
        List<Assignment> failLs = new ArrayList<>();
        FacesMessage message = null;
        for (Assignment assignment : selectedAssignments) {
            assignment.setStatus(!assignment.isStatus());
            result = CtrlAssignment.update(assignment);
            if (result.equals(CtrlAssignment.ERROR)) {
                failLs.add(assignment);
            }
        }
        if (failLs.size() > 0) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Change status error", failLs.toString() + " can't change.\nPlease try again !");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Change status Infomation", "Change status success");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        updateAssignmentList();
    }
    private void updateAssignmentList(){
        mediumAssignmentsModel = new AssignmentDataModel(CtrlAssignment.getAllAssignment());
    }

    public void showSolution(Assignment assignment) {
        currentAssignment = assignment;
    }

    public List<Subject> getSubjects() {

        return subjects;
    }

    public void closeDialog() {

    }

    public List<Course> getCourses() {
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

    public String getDialogAddPath() {
        return dialogAddPath;
    }

    public String getDialogEditPath() {
        return dialogEditPath;
    }

    public String getDialogViewPath() {
        return dialogViewPath;
    }

    public List<Batch> getBatchs() {
        return batchs;
    }

    public void setBatchs(List<Batch> batchs) {
        this.batchs = batchs;
    }

    public void setCurrentAssignment(Assignment currentAssignment) {
        this.currentAssignment = currentAssignment;
    }



    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getDialogSolutionPath() {
        return dialogSolutionPath;
    } 
 
    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

}
