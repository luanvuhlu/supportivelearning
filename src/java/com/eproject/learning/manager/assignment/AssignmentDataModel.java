/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.assignment;

import com.eproject.learning.manager.batch.*;
import com.eproject.learning.entity.Assignment;
import com.eproject.learning.entity.Course;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author luan
 */
public class AssignmentDataModel  extends ListDataModel<Assignment> implements SelectableDataModel<Assignment>, Serializable {
    public AssignmentDataModel() {
    }
     public AssignmentDataModel(List<Assignment> data) {  
        super(data);  
    }
    @Override
    public Object getRowKey(Assignment t) {
        return t.getAssignmentId();
    }

    @Override
    public Assignment getRowData(String key) {
        List<Assignment> batchs=(List<Assignment>) getWrappedData();
        for(Assignment batch : batchs) {  
            if(key.toString().equalsIgnoreCase(batch.getAssignmentId()+""))
                return batch;  
        }  
        return null;
    }



}
