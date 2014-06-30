/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eproject.learning.manager.course;

import com.eproject.learning.controller.Convert;
import com.eproject.learning.controller.CtrlBatch;
import com.eproject.learning.controller.CtrlCourse;
import com.eproject.learning.controller.CtrlSemester;
import com.eproject.learning.entity.Batch;
import com.eproject.learning.entity.Course;
import com.eproject.learning.entity.Semester;
import com.eproject.learning.entity.Subject;
import com.eproject.learning.entity.SubjectSemester;
import com.eproject.learning.manager.semester.SemesterTable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author luan
 */
@ManagedBean
@ViewScoped
@SessionScoped
public class CourseTable implements Serializable {

    private boolean disable = true;
    private List<Course> courses = CtrlCourse.getAllCourse();
    private Course course;
    private Course[] selectedCourses;

    private CourseDataModel mediumCoursesModel;
    
    private final String dialogViewPath="course-detail.xhtml";
    
    public void select() {
        if (selectedCourses == null || selectedCourses.length < 1) {
            disable = true;
        } else {
            disable = false;
        }
    }
    
    public void delete() {
        String result = "";
        List<Course> failLs = new ArrayList<>();
        FacesMessage message = null;
        for (Course course : selectedCourses) {
            if(course.getBatchs()!=null && course.getBatchs().size() >0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete error", "Course "+course.getCode()+" has some batchs. Please delete batchs before !");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                return;
            }
            
            result = CtrlCourse.delete(course.getCourseId());
            if (result.equals(CtrlCourse.ERROR)) {
                failLs.add(course);
            }
        }
        if (failLs.size() > 0) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete error", failLs.toString() + " are'nt deleted.\nPlease try again !");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Infomation", "Delete success");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        updateBatchList();
    }
    
    public List<Semester> getSemesters(){
        if(course !=null && course.getSemesters() !=null){
            return CtrlSemester.sortSemester(new Convert().setToList(course.getSemesters()));
        }
        return new ArrayList<>();
    }
    
    public List<Subject> getSubjects(Semester semester){
        List<Subject> subjects=new ArrayList<>();
        
        if(semester !=null && semester.getSubjectSemesters() !=null){
            for(SubjectSemester ss:semester.getSubjectSemesters()){
                if(ss.getPk().getSubject() !=null){
                    
                    subjects.add(ss.getPk().getSubject());
                }
            }
        }
        return subjects;
    }

    private void updateBatchList() {
        mediumCoursesModel = new CourseDataModel(CtrlCourse.getAllCourse());
    }
    
    public void showDetail(Course course){
        this.course=course;
    }
    


    public CourseTable() {
        mediumCoursesModel = new CourseDataModel(courses);
    } 

    public Course getCourse() {
        return course; 
    }

    public List<Course> getCourses() {
        return courses; 
    }

    public String getDialogViewPath() {
        return dialogViewPath;
    }

    public CourseDataModel getMediumCoursesModel() {
        return mediumCoursesModel;
    }

    public Course[] getSelectedCourses() {
        return selectedCourses;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void setMediumCoursesModel(CourseDataModel mediumCoursesModel) {
        this.mediumCoursesModel = mediumCoursesModel;
    }

    public void setSelectedCourses(Course[] selectedCourses) {
        this.selectedCourses = selectedCourses;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public boolean isDisable() {
        return disable;
    }

   
    

}
