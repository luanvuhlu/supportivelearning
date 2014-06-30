/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.semester;

import com.eproject.learning.entity.Batch;
import com.eproject.learning.entity.Semester;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author luan
 */
public class SemesterDataModel   extends ListDataModel<Semester> implements SelectableDataModel<Semester>, Serializable {

    public SemesterDataModel() {
    }
     public SemesterDataModel(List<Semester> data) {  
        super(data);  
    }
    @Override
    public Object getRowKey(Semester t) {
        return t.getSemesterId();
    }

    @Override
    public Semester getRowData(String key) {
        List<Semester> semesters=(List<Semester>) getWrappedData();
        for(Semester semester : semesters) {  
            if(key.toString().equalsIgnoreCase(semester.getSemesterId()+""))
                return semester;  
        }  
        return null;
    }
    
}
