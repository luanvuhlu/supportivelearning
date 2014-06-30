/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.student;

import com.eproject.learning.manager.user.*;
import com.eproject.learning.manager.semester.*;
import com.eproject.learning.entity.Batch;
import com.eproject.learning.entity.Student;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author luan
 */
public class StudentDataModel extends ListDataModel<Student> implements SelectableDataModel<Student>, Serializable {

    public StudentDataModel() {
    }
     public StudentDataModel(List<Student> data) {  
        super(data);  
    }
    @Override
    public Object getRowKey(Student t) {
        return t.getStudentId();
    }

    @Override
    public Student getRowData(String key) {
        List<Student> students=(List<Student>) getWrappedData();
        for(Student student : students) {  
            if(key.toString().equalsIgnoreCase(student.getStudentId()+""))
                return student;  
        }  
        return null;
    }
    
}
