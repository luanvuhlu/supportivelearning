/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author luan
 */
@Embeddable
public class MarkAssignmentId implements Serializable{

    private Assignment assignment;
      
    private Student student;
    
    @ManyToOne
    public Assignment getAssignment() {
        return assignment;
    }
    @ManyToOne
    public Student getStudent() {
        return student;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    

    
    public boolean equals(Object o) {
        if (this== o) return true;
        if (o ==null|| getClass() != o.getClass()) return false;
 
        MarkAssignmentId that = (MarkAssignmentId) o;
 
        if (assignment !=null?!assignment.equals(that.assignment) : that.assignment !=null) return false;
        if (student !=null?!student.equals(that.student) : that.student !=null)
            return false;
 
        return true;
    }
 
    public int hashCode() {
        int result;
        result = (assignment !=null? assignment.hashCode() : 0);
        result =31* result + (student !=null? student.hashCode() : 0);
        return result;
    }
}
