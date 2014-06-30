/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eproject.learning.entity;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "student")
public class Student implements Serializable {

        private int studentId;
        private String username;
        private String password;
        private Date dateCreate;
        private String lastName;
        private String firstName;
        private Date birthday;

    private boolean gender;
        private String phone;
        private String email;
        private String address;

    private boolean status;
        private String avarta;
        private String roll;

        private Set<Feedback> feedbacks = new HashSet<>(0);

        private Batch batch;

        private Set<MarkAssignment> markAssignments = new HashSet<>(0);

    public Student() {
    }

@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Student_ID", unique = true, nullable = false)
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

@Column(name = "Username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

@Column(name = "Password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

@Column(name = "DateCreate")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

@Column(name = "LastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

@Column(name = "FirstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

@Column(name = "Birthday")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Column(name = "Gender")
    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

@Column(name = "Phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

@Column(name = "Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

@Column(name = "Address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "Status")
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

@Column(name = "Avarta")
    public String getAvarta() {
        return avarta;
    }

    public void setAvarta(String avarta) {
        this.avarta = avarta;
    }

@OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    public Set<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "batchId", nullable = false)
    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

//    @Access(AccessType.PROPERTY)
@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.student")
    public Set<MarkAssignment> getMarkAssignments() {
        return markAssignments;
    }

    public void setMarkAssignments(Set<MarkAssignment> markAssignments) {
        this.markAssignments = markAssignments;
    }

@Column(name = "Roll")
    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

//    public String getFullName() {
//        if (this.getFirstName() == null || this.getLastName() == null) {
//            return "Unknown";
//        }
//        return this.getFirstName() + " " + this.getLastName();
//    }
}
