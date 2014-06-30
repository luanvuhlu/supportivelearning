/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eproject.learning.entity;

import java.util.Date;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author Administrator
 */
@ManagedBean
@Entity
@Table(name = "markassignment")
@AssociationOverrides({
    @AssociationOverride(name = "pk.assignment", joinColumns = @JoinColumn(name = "AssignmentId")),
    @AssociationOverride(name = "pk.student", joinColumns = @JoinColumn(name = "StudentId"))})
public class MarkAssignment implements Serializable {

        private String filePath;
        private float mark;
        private Date date;

    private MarkAssignmentId pk = new MarkAssignmentId();

@EmbeddedId
        public MarkAssignmentId getPk() {
        return pk;
    }

    @Transient
    public Assignment getAssignment() {
        return getPk().getAssignment();
    }

    public void setAssignment(Assignment assignment) {
        getPk().setAssignment(assignment);
    }

    @Transient
    public Student getStudent() {
        return getPk().getStudent();
    }

    public void setStudent(Student student) {
        getPk().setStudent(student);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MarkAssignment that = (MarkAssignment) o;

        if (getPk() != null ? !getPk().equals(that.getPk()) : that.getPk() != null) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        return (getPk() != null ? getPk().hashCode() : 0);
    }

@Column(name = "FilePath")
    public String getFilePath() {
        return filePath;
    }

@Column(name = "Mark")
    public float getMark() {
        return mark;
    }

@Column(name = "Date")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getDate() {
        return date;
    }

    public MarkAssignment() {
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPk(MarkAssignmentId pk) {
        this.pk = pk;
    }
}
