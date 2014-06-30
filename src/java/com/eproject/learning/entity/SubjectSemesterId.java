/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 *
 * @author luan
 */
@Embeddable
public class SubjectSemesterId implements Serializable{
//
//    private Integer subjectId;
//
//    private Integer semesterId;
    private Subject subject;
    private Semester semester;

    public SubjectSemesterId() {
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

//    public Integer getSemesterId() {
//        return semesterId;
//    }
//
//    public Integer getSubjectId() {
//        return subjectId;
//    }
//
//    public void setSemesterId(Integer semesterId) {
//        this.semesterId = semesterId;
//    }
//
//    public void setSubjectId(Integer subjectId) {
//        this.subjectId = subjectId;
//    }
@ManyToOne
    public Subject getSubject() {
        return subject;
    }
    

//   public int hashCode() {
//    return (int)(subjectId + semesterId);
//  }
// 
//  public boolean equals(Object object) {
//    if (object instanceof SubjectSemesterId) {
//      SubjectSemesterId otherId = (SubjectSemesterId) object;
//      return (otherId.subjectId == this.subjectId) && (otherId.semesterId == this.semesterId);
//    }
//    return false;
//  }
@ManyToOne
    public Semester getSemester() {
        return semester;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
 
        SubjectSemesterId that = (SubjectSemesterId) o;
 
        if (semester != null ? !semester.equals(that.semester) : that.semester != null) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null)
            return false;
 
        return true;
    }
 
    public int hashCode() {
        int result;
        result = (semester != null ? semester.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        return result;
    }
}
