/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.student;

import com.eproject.learning.controller.Convert;
import com.eproject.learning.manager.student.*;
import com.eproject.learning.controller.CtrlStudent;
import com.eproject.learning.controller.CtrlStudent;
import com.eproject.learning.entity.MarkAssignment;
import com.eproject.learning.entity.Student;
import com.eproject.learning.entity.Student;
import com.eproject.learning.view.ActionBean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
public class ViewStudent implements Serializable{
    private Student student;
    private List<MarkAssignment> marks;
    public ViewStudent() {
        String studentPara=ActionBean.getParameter("s");
        if(studentPara.equalsIgnoreCase("")){
            ActionBean.redirect("manager.xhtml?action=student");
            return;
        }else {
            student=CtrlStudent.getById(Integer.parseInt(studentPara));
        }
        marks = new Convert().setToList(student.getMarkAssignments());
    }
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<MarkAssignment> getMarks() {
        return marks;
    }

    public void setMarks(List<MarkAssignment> marks) {
        this.marks = marks;
    }
    
    
   
}
