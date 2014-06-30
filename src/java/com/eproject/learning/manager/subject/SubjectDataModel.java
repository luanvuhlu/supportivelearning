/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.subject;


import com.eproject.learning.entity.Subject;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author luan
 */
public class SubjectDataModel  extends ListDataModel<Subject> implements SelectableDataModel<Subject>, Serializable {
    public SubjectDataModel() {
    }
     public SubjectDataModel(List<Subject> data) {  
        super(data);  
    }
    @Override
    public Object getRowKey(Subject t) {
        return t.getSubjectId();
    }

    @Override
    public Subject getRowData(String key) {
        List<Subject> Subjects=(List<Subject>) getWrappedData();
        for(Subject Subject : Subjects) {  
            if(key.toString().equalsIgnoreCase(Subject.getSubjectId()+""))
                return Subject;  
        }  
        return null;
    }



}
