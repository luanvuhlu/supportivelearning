/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eproject.learning.entity;

import java.io.Serializable;
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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author luan
 */
@Entity
@Table(name = "subject_semester")
//@IdClass(SubjectSemesterId.class)
@AssociationOverrides({
		@AssociationOverride(name = "pk.semester", 
			joinColumns = @JoinColumn(name = "SemesterId")),
		@AssociationOverride(name = "pk.subject", 
			joinColumns = @JoinColumn(name = "SubjectId")) })
public class SubjectSemester implements Serializable {
//    @Id
//    private Integer subjectId;
//    @Id
//    private Integer semesterId;
//    @ManyToOne
//    @JoinColumn(name = "SubjectId")
//    private Subject subject;
//    @ManyToOne
//    @JoinColumn(name = "SemesterId")
//    private Semester semester;

    private SubjectSemesterId pk;
        private Integer subjectOrder;

    public SubjectSemester() {
    }

    @EmbeddedId
    public SubjectSemesterId getPk() {
        return pk;
    }

    public void setPk(SubjectSemesterId pk) {
        this.pk = pk;
    }

    public void setSemester(Semester semester) {
        this.getPk().setSemester(semester);
    }

    public void setSubject(Subject subject) {
        this.getPk().setSubject(subject);
    }

    @Transient
    public Semester getSemester() {
        return getPk().getSemester();
    }

    @Transient
    public Subject getSubject() {
        return getPk().getSubject();
    }
    
    public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
 
		SubjectSemester that = (SubjectSemester) o;
 
		if (getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null)
			return false;
 
		return true;
	}
 
	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}

//    public long getSubjectId() {
//        return subjectId;
//    }
//
//    public void setSubjectId(Integer subjectId) {
//        this.subjectId = subjectId;
//    }
//
//    public Integer getSemesterId() {
//        return semesterId;
//    }
//
//    public void setSemesterId(Integer semesterId) {
//        this.semesterId = semesterId;
//    }
//
//    public Subject getSubject() {
//        return subject;
//    }
//
//    public void setSubject(Subject subject) {
//        this.subject = subject;
//    }
//
//    public Semester getSemester() {
//        return semester;
//    }
//
//    public void setSemester(Semester semester) {
//        this.semester = semester;
//    }

@Column(name = "SubjectOrder")
    public Integer getSubjectOrder() {
        return subjectOrder;
    }

    public void setSubjectOrder(Integer subjectOrder) {
        this.subjectOrder = subjectOrder;
    }



}
