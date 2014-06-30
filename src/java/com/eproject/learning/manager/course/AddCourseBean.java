/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.course;

import com.eproject.learning.controller.CtrlCourse;
import com.eproject.learning.entity.Course;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author luan
 */
@ManagedBean
public class AddCourseBean {
    private String code;
    private String name;
    private String time;

    public void addNewCourse(){
         String msg="Unknow";
         String title="Add Course";
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(title, msg));
         String result="";
         Course newCourse=new Course();
         newCourse.setCode(code);
         newCourse.setName(name);
         newCourse.setTime(time);
         if(CtrlCourse.isUnique(newCourse)){
             msg="Code and Name must be unique !";
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(title, msg));
             return;
         }
        result=CtrlCourse.add(newCourse);
        if(result==CtrlCourse.ERROR){
                msg="Error, Please try again !";
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(title, msg));
             return;
        }
        
        if(result==CtrlCourse.SUCCESS){
            msg="Success !";
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(title, msg));
             return;
        }

    }
    
    //Don't delete above
    public AddCourseBean() {
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    
    

    
}
