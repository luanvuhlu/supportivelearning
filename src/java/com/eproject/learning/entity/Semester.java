/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eproject.learning.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@ManagedBean
@Entity
@Table(name = "semester")
public class Semester implements Serializable {

    private Integer semesterId;
    private Integer semesterOrder;
    private String name;

    private Set<Batch> batchs = new HashSet<>(0);

    private Course course;

//    @OneToMany(mappedBy = "semester")
    private Set<SubjectSemester> subjectSemesters = new HashSet<>(0);

    public Semester() {
        course = new Course();
    }

    public Semester(Integer id) {
        this.semesterId = semesterId;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Semester_ID", unique = true, nullable = false)
    public Integer getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Integer semesterId) {
        this.semesterId = semesterId;
    }

    @Column(name = "SemesterOrder")
    public Integer getSemesterOrder() {
        return semesterOrder;
    }

    public void setSemesterOrder(Integer semesterOrder) {
        this.semesterOrder = semesterOrder;
    }

    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "semester")
    public Set<Batch> getBatchs() {
        return batchs;
    }

    public void setBatchs(Set<Batch> batchs) {
        this.batchs = batchs;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CourseId", nullable = false)
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.semester", cascade = CascadeType.ALL)
    public Set<SubjectSemester> getSubjectSemesters() {
        return subjectSemesters;
    }

    public void setSubjectSemesters(Set<SubjectSemester> subjectSemesters) {
        this.subjectSemesters = subjectSemesters;
    }

}
