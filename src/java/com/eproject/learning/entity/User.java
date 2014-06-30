/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eproject.learning.entity;

import com.eproject.learning.controller.CtrlRole;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;

/**
 *
 * @author Administrator
 */
@ManagedBean
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "User_ID", unique = true, nullable = false)
    private Integer userId;
    @Column(name = "Username")
    private String username;
    @Column(name = "Password")
    private String password;
    @Column(name = "DateCreate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCreate;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "Birthday")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthday;
    @Column(name = "Gender")
    private boolean gender=true;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "Email")
    private String email;
    @Column(name = "Address")
    private String address;
    @Column(name = "Status")
    private boolean status=true;
    @Column(name = "Avarta")
    private String avarta;
    

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = { 
		@JoinColumn(name = "UserId", nullable = false, updatable = false) }, 
                    inverseJoinColumns = { 
                @JoinColumn(name = "RoleId", 
					nullable = false, updatable = false) })
    private Set<Role> roles=new HashSet<>(0);
    
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "staff_subject",
            joinColumns = { 
		@JoinColumn(name = "UserId", nullable = false, updatable = false) }, 
                    inverseJoinColumns = { 
                @JoinColumn(name = "SubjectId", 
					nullable = false, updatable = false) })
    private Set<Subject> subjects=new HashSet<>(0);
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Assignment> assignments= new HashSet<>(0);
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Batch> batchs=new HashSet<Batch>(0);

    public User() {
    }
    public User(Integer id) {
        this.userId=id;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAvarta() {
        return avarta;
    }

    public void setAvarta(String avarta) {
        this.avarta = avarta;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Set<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(Set<Assignment> assignments) {
        this.assignments = assignments;
    }

    public Set<Batch> getBatchs() {
        return batchs;
    }

    public void setBatchs(Set<Batch> batchs) {
        this.batchs = batchs;
    }

    public String getFullName(){
        if(this.getFirstName() ==null || this.getLastName() ==null ){
            return "Unknown";
        }
        return this.getFirstName()+" "+this.getLastName();
    }
    public boolean isStaff(){
        for(Role role:this.getRoles()){
            role=CtrlRole.getRoleById(role.getRoleId());
            if(role.getName().equalsIgnoreCase("staff")){
                return true;
            }
        }
        return false;
    }


    
}   
