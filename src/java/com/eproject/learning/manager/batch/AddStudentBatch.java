/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.batch;

import com.eproject.learning.controller.CtrlBatch;
import com.eproject.learning.controller.CtrlStudent;
import com.eproject.learning.entity.Batch;
import com.eproject.learning.entity.Student;
import com.eproject.learning.view.ActionBean;
import com.sun.corba.se.spi.orbutil.fsm.ActionBase;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
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
public class AddStudentBatch implements Serializable{

    private Batch batch;
    private Student student;
    public AddStudentBatch() {
        String batchPara=ActionBean.getParameter("b");
        if(batchPara.equals("")){
            System.out.println("Redirect");
            return;
        }
        batch=CtrlBatch.getByName(batchPara);
        student=new Student();
    }
    public void add() throws IOException{
        boolean success = false;
        student.setBatch(batch);
        student.setPassword("bbbbbb");
        student.setRoll(student.getLastName()+student.getBatch().getStudents().size());
        student.setDateCreate(new Date());
        student.setUsername(student.getFirstName()+student.getBatch().getName().toLowerCase());
        RequestContext context = RequestContext.getCurrentInstance();
        String msg = "";
        String title = "Add Student";
        FacesMessage fMsg = null;
        String result = "";
        if (student == null) {
            msg = "Error";
            fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        } else {
            result=CtrlStudent.add(student);
            if (result.equals(CtrlStudent.SUCCESS)) {
                msg = "Success";
                fMsg = new FacesMessage(title, msg);
                success = true;
            } else {
                if (result.equals(CtrlStudent.ERROR)) {
                    msg = "Error";
                    fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
                }
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, fMsg);
        context.addCallbackParam("update", success);
        
        batch=CtrlBatch.getBatchById(batch.getBatchId());
        student=new Student();
    }

    public Batch getBatch() {
        return batch;
    }

    public Student getStudent() {
        return student;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
    
}
