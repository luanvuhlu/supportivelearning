/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.course;

import com.eproject.learning.entity.Course;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author luan
 */
public class CourseDataModel extends ListDataModel<Course> implements SelectableDataModel<Course>, Serializable{

    public CourseDataModel() {
    }
     public CourseDataModel(List<Course> data) {  
        super(data);  
    }
    @Override
    public Object getRowKey(Course t) {
        return t.getCourseId();
    }

    @Override
    public Course getRowData(String key) {
        List<Course> courses=(List<Course>) getWrappedData();
        for(Course course : courses) {  
            if(key.toString().equalsIgnoreCase(course.getCourseId()+""))
                return course;  
        }  
        return null;
    }
    
}
