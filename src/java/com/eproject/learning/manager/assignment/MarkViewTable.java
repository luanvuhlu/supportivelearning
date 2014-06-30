/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eproject.learning.manager.assignment;

import com.eproject.learning.authentication.Authentication;
import com.eproject.learning.controller.Convert;
import com.eproject.learning.controller.CtrlStudent;
import com.eproject.learning.entity.MarkAssignment;
import com.eproject.learning.entity.Student;
import com.eproject.learning.view.ActionBean;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author luan
 */
@ManagedBean
@ViewScoped
public class MarkViewTable implements Serializable {

    private List<MarkAssignment> marks;

    public MarkViewTable() {

        Student student = Authentication.getStudent();
        if (student == null) {
            ActionBean.redirect("manager.xhtml?action=studentassignment");
            return;
        }
        marks = new Convert().setToList(student.getMarkAssignments());

    }

    public List<MarkAssignment> getMarks() {
        return marks;
    }

    public void setMarks(List<MarkAssignment> marks) {
        this.marks = marks;
    }
}
