/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.assignment;

import com.eproject.learning.controller.Convert;
import com.eproject.learning.controller.CtrlAssignment;
import com.eproject.learning.controller.CtrlMark;
import com.eproject.learning.entity.Assignment;
import com.eproject.learning.entity.MarkAssignment;
import com.eproject.learning.view.ActionBean;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author luan
 */
@ManagedBean
@ViewScoped
public class MarkTable implements Serializable{
    private Assignment assignment;
    private List<MarkAssignment> marks;
    public MarkTable() {
        String assPara=ActionBean.getParameter("as");
        if(assPara.equalsIgnoreCase("")){
            ActionBean.redirect("manager.xhtml?action=assignment");
            return;
        }else {
            assignment=CtrlAssignment.getById(Integer.parseInt(assPara));
            marks=new Convert().setToList(assignment.getMarkAssignments());
        }
    }
    
    public void editMark(RowEditEvent event){   
        MarkAssignment mark=(MarkAssignment)event.getObject();
        String result=CtrlMark.update(mark, mark.getMark());
        System.out.println("Mark: "+mark.getMark()+".....................");
        if(result.equals(CtrlMark.SUCCESS)){
            System.out.println("Update Success..............");
            return;
        }
        System.out.println("Update fail..............");
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public List<MarkAssignment> getMarks() {
        return marks;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public void setMarks(List<MarkAssignment> marks) {
        this.marks = marks;
    }
    
    
}
