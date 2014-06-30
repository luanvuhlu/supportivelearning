/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.semester;

import com.eproject.learning.controller.CtrlSemester;
import com.eproject.learning.entity.Course;
import com.eproject.learning.entity.Semester;
import com.eproject.learning.entity.Subject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author luan
 */
@ManagedBean
@ViewScoped
@SessionScoped
public class SemesterTable {
    private Semester selectedSemester;
    private SemesterDataModel mediumSemestersModel;
    private List<Semester> semesters=CtrlSemester.getAllSemesters();
    private Subject[] subjectsInSemester;

    public SemesterTable() {
        mediumSemestersModel=new SemesterDataModel(semesters);
    }
    public void setSubjectInSemester(Semester semester){
        
    }
    
    
    public Semester[] getSemestersInCourse(Course course){
        Set<Semester> semesters=null;
        try{
            semesters=course.getSemesters();
        }catch(NullPointerException ex){
            ex.printStackTrace();
            semesters=new HashSet<>();
        }
        Semester[] sArr=semesters.toArray(new Semester[semesters.size()]);
        return sArr;
    }
    
    public void deleteSemester(ActionEvent event){
        FacesMessage fMsg=new FacesMessage("Semester Update", selectedSemester.getSemesterId()+"");
        FacesContext.getCurrentInstance().addMessage(null, fMsg);
        String result="";
        
    }
    

    // Don't delete above
    public Semester getSelectedSemester() {
        return selectedSemester;
    }

    public void setSelectedSemester(Semester selectedSemester) {
        this.selectedSemester = selectedSemester;
    }

    public List<Semester> getSemesters() {
        return semesters;
    }

    public void setSemesters(List<Semester> semesters) {
        this.semesters = semesters;
    }

    public SemesterDataModel getMediumSemestersModel() {
        return mediumSemestersModel;
    }

    public void setMediumSemestersModel(SemesterDataModel mediumSemestersModel) {
        this.mediumSemestersModel = mediumSemestersModel;
    }

    public Subject[] getSubjectsInSemester() {
        return subjectsInSemester;
    }

    public void setSubjectsInSemester(Subject[] subjectsInSemester) {
        this.subjectsInSemester = subjectsInSemester;
    }
    
    
    
}
