/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eproject.learning.entity;

import java.util.Date;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.persistence.Access;
import javax.persistence.AccessType;
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
import javax.persistence.Temporal;
import org.hibernate.annotations.Cascade; 
/**
 *
 * @author Administrator
 */
@ManagedBean
@Entity
@Table(name = "assignment")
public class Assignment implements Serializable{

        private int assignmentId;
        private String name;
        private String filePath;
        private String description;
        private Date startDate;
        private Date endDate;
    
    private boolean status;
    
    private boolean type;
    
        private User user;

        private Batch batch;
    
        private Subject subject;
    
    
    private Set<MarkAssignment> markAssignments=new HashSet<MarkAssignment>(0);

    public Assignment() {
    }

    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Assignment_ID", unique = true, nullable = false)
    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

@Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

@Column(name = "FilePath")
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

@Column(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

@Column(name = "StartDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

@Column(name = "EndDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "Status")
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Column(name = "Type")
    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BatchId", nullable = false)
    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SubjectId", nullable = false)
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject; 
    }

//    @Access(AccessType.PROPERTY)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.assignment", cascade=CascadeType.ALL)
    public Set<MarkAssignment> getMarkAssignments() {
        return markAssignments;
    }

    public void setMarkAssignments(Set<MarkAssignment> markAssignments) {
        this.markAssignments = markAssignments;
    }

    


   
}
