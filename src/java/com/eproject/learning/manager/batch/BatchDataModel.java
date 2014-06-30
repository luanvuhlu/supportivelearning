/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.batch;

import com.eproject.learning.entity.Batch;
import com.eproject.learning.entity.Course;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author luan
 */
public class BatchDataModel  extends ListDataModel<Batch> implements SelectableDataModel<Batch>, Serializable {
    public BatchDataModel() {
    }
     public BatchDataModel(List<Batch> data) {  
        super(data);  
    }
    @Override
    public Object getRowKey(Batch t) {
        return t.getBatchId();
    }

    @Override
    public Batch getRowData(String key) {
        List<Batch> batchs=(List<Batch>) getWrappedData();
        for(Batch batch : batchs) {  
            if(key.toString().equalsIgnoreCase(batch.getBatchId()+""))
                return batch;  
        }  
        return null;
    }



}
