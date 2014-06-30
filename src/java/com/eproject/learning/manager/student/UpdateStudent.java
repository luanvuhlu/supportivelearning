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
import com.eproject.learning.view.ActionBean;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author luan
 */
@ManagedBean
@ViewScoped
@SessionScoped

public class UpdateStudent implements Serializable {

    private Student student;
    private boolean addMode = false;
    private Batch batch;
    private List<Batch> allBatchs = CtrlBatch.getAll();

    public UpdateStudent() {
        String studentPara = ActionBean.getParameter("s");
        if (ActionBean.getParameter("a").equals("add")) {
            System.out.println("Add");
            addMode = true;
            student = new Student();

        } else if (studentPara.equalsIgnoreCase("")) {
            return;
        } else {
            student = CtrlStudent.getById(Integer.parseInt(studentPara));
            batch = student.getBatch();
        }
    }

    public void chooseBatch() {
        RequestContext.getCurrentInstance().openDialog("/student/select-batch.xhtml");
    }

    public void onBatchChosen(SelectEvent event) {
        batch = (Batch) event.getObject();
        student.setBatch(batch);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Batch Selected", "Name: " + batch.getName());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void selectBatchFromDialog(Batch batch) {
        RequestContext.getCurrentInstance().closeDialog(batch);
    }

    public void save() {
        String msg = "";
        String title = "";
        FacesMessage fMsg = null;
        String result = "";
        if (student.getStudentId() == 0) {
            title = "Add Student";
            student.setDateCreate(new Date());
            result = CtrlStudent.add(student);
        } else {
            title = "Update Student";
            result = CtrlStudent.update(student);
        }
        if (result == CtrlStudent.ERROR) {
            msg = "Error";
            fMsg = new FacesMessage(title, msg);
        } else {
            msg = "Success";
            fMsg = new FacesMessage(title, msg);

            reset();
        }
        FacesContext.getCurrentInstance().addMessage(null, fMsg);
    }

    public void reset() {
        if (addMode) {
            student = new Student();
            return;
        }
        student = CtrlStudent.getById(student.getStudentId());
    }

    public Student getstudent() {
        return student;
    }

    public void setstudent(Student student) {
        this.student = student;
    }

    public boolean isAddMode() {
        return addMode;
    }

    public void setAddMode(boolean addMode) {
        this.addMode = addMode;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public List<Batch> getAllBatchs() {
        return allBatchs;
    }

    public void setAllBatchs(List<Batch> allBatchs) {
        this.allBatchs = allBatchs;
    }

}
