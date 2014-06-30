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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 *
 * @author Administrator
 */
@ManagedBean
@Entity
@Table(name="course")
public class Course implements Serializable{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Course_ID", unique = true, nullable = false)
    private Integer courseId;
    @Column(name = "Code")
    private String code;
    @Column(name = "Name")
    private String name;
    @Column(name = "Time")
    private String time;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    private Set<Batch> batchs=new HashSet<>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Semester> semesters=new HashSet<>(0);

    public Course() {
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Set<Batch> getBatchs() {
        return batchs;
    }

    public void setBatchs(Set<Batch> batchs) {
        this.batchs = batchs;
    }

    public Set<Semester> getSemesters() {
        return semesters;
    }

    public void setSemesters(Set<Semester> semesters) {
        this.semesters = semesters;
    }  
    @Override
    public String toString(){
        return this.getCourseId()+"";
    }
}
