/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.batch;

import com.eproject.learning.controller.Convert;
import com.eproject.learning.controller.CtrlBatch;
import com.eproject.learning.controller.CtrlCourse;
import com.eproject.learning.controller.CtrlSemester;
import com.eproject.learning.controller.CtrlStudent;
import com.eproject.learning.controller.CtrlSubject;
import com.eproject.learning.controller.CtrlUser;
import com.eproject.learning.entity.Batch;
import com.eproject.learning.entity.Course;
import com.eproject.learning.entity.Semester;
import com.eproject.learning.entity.Student;
import com.eproject.learning.entity.Subject;
import com.eproject.learning.entity.User;
import com.eproject.learning.view.ActionBean;
import com.sun.corba.se.spi.orbutil.fsm.ActionBase;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
public class EditBatch extends BatchManager implements Serializable{

    private Batch batch;
    private boolean addMode=false;
    private String semesterId;
    private String userId;
    private String subjectId;
    private String courseId;
    private Semester semester; 
    private User user;
    public EditBatch() {
        String batchPara=ActionBean.getParameter("b");
        if(ActionBean.getParameter("a").equals("add")){
            System.out.println("Add");
            addMode=true;
            batch=new Batch();
        }else if(batchPara.equalsIgnoreCase("")){
            ActionBean.redirect("manager.xhtml?action=batch");
            return;
        }else {
            batch=CtrlBatch.getBatchById(Integer.parseInt(batchPara));
            courseId=batch.getCourse().getCourseId().toString();
            semesterId=batch.getSemester().getSemesterId().toString();
            subjectId=batch.getSubject().getSubjectId().toString();
            userId=batch.getUser().getUserId().toString();
            addMode=false;
        }
    }
    public void reset(){
        courseId="";
            semesterId="";
            subjectId="";
            userId="";
        if(addMode){
            batch=new Batch();
            return;
        }
        batch=CtrlBatch.getBatchById(batch.getBatchId());
        courseId=batch.getCourse().getCourseId().toString();
            semesterId=batch.getSemester().getSemesterId().toString();
            subjectId=batch.getSubject().getSubjectId().toString();
            userId=batch.getUser().getUserId().toString();
    }
    public void save() {
        boolean success = false;
        batch.setSemester(CtrlSemester.getSemesterById(Integer.parseInt(semesterId)));
        batch.setSubject(CtrlSubject.getById(Integer.parseInt(subjectId)));
        batch.setUser(CtrlUser.getById(Integer.parseInt(userId)));
        
        RequestContext context = RequestContext.getCurrentInstance();
        String msg = "";
        String title = "";
        FacesMessage fMsg = null;
        String result = "";
        if (batch == null) {
            msg = "Error";
            fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        } else {
            if (batch.getBatchId()==null || batch.getBatchId() == 0) {
                title = "Add Batch";
                batch.setCourse(CtrlCourse.getById(Integer.parseInt(courseId)));
                result = CtrlBatch.add(batch);
            } else {
                title = "Edit Batch";
                result = CtrlBatch.update(batch);
            }
            if (result.equals(CtrlBatch.SUCCESS)) {
                msg = "Success";
                fMsg = new FacesMessage(title, msg);
                success = true;
                reset();
            } else {
                if (result.equals(CtrlBatch.ERROR)) {
                    msg = "Error";
                    fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
                }
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, fMsg);
        context.addCallbackParam("update", success);
        
    }
    
    public Map<String, Integer> getSemestersOfCourse(){
        if(addMode)
            return getSemestersOfCourse(courseId);
        return getSemestersOfCourse(batch);
    }
    public Map<String, Integer> getSubjectsOfSemester(){
        return getSubjectsOfSemester(semesterId);
    }
    public Map<String, Integer> getUsersHaveSubject(){
        return getUsersHaveSubject(subjectId);
    }

    @Override
    public Map<String, Integer> getAllCourse() {
        return super.getAllCourse(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Batch getBatch() {
        return batch;
    }

    public Semester getSemester() {
        return semester;
    }


    public User getUser() {
        return user;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setSemesterId(String semesterId) {
        this.semesterId = semesterId;
    }

    public String getUserId() {
        return userId;
    }

    public String getSemesterId() {
        return semesterId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setAddMode(boolean addMode) {
        this.addMode = addMode;
    }

    public boolean isAddMode() {
        return addMode;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
