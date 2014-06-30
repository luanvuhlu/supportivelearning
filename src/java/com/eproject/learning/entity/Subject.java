/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.entity;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import java.util.HashSet;
import java.util.Set;
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
@Table(name = "subject")
public class Subject implements Serializable {

        private Integer subjectId;
        private String name;
        private String code;
    
        private Set<Assignment> assignments=new HashSet<>(0);
    
        private Set<Batch> batchs=new HashSet<Batch>(0);
    
        private Set<User> users=new HashSet<>(0);
    
//    @OneToMany(mappedBy = "subject")
    private Set<SubjectSemester> subjectSemesters=new HashSet<>(0);
    

    public Subject() {
    }
    public Subject(Integer id) {
        this.subjectId=id;
    }

@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Subject_ID", unique = true, nullable = false)
    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

@Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

@Column(name = "Code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

@OneToMany(fetch = FetchType.LAZY, mappedBy = "subject")
    public Set<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(Set<Assignment> assignments) {
        this.assignments = assignments;
    }

    

@ManyToMany(fetch = FetchType.LAZY, mappedBy = "subjects", cascade = CascadeType.ALL)
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.subject")
    public Set<SubjectSemester> getSubjectSemesters() {
        return subjectSemesters;
    }

    public void setSubjectSemesters(Set<SubjectSemester> subjectSemesters) {
        this.subjectSemesters = subjectSemesters;
    }

   

@OneToMany(fetch = FetchType.LAZY, mappedBy = "subject", cascade = CascadeType.ALL)
    public Set<Batch> getBatchs() {
        return batchs;
    }

    public void setBatchs(Set<Batch> batchs) {
        this.batchs = batchs;
    }

}
