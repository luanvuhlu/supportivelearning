/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.feedback;


import com.eproject.learning.manager.feedback.*;
import com.eproject.learning.entity.Feedback;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author luan
 */
public class FeedbackDataModel  extends ListDataModel<Feedback> implements SelectableDataModel<Feedback>, Serializable {
    public FeedbackDataModel() {
    }
     public FeedbackDataModel(List<Feedback> data) {  
        super(data);  
    }
    @Override
    public Object getRowKey(Feedback t) {
        return t.getFeedbackId();
    }

    @Override
    public Feedback getRowData(String key) {
        List<Feedback> feedbacks=(List<Feedback>) getWrappedData();
        for(Feedback feedback : feedbacks) {  
            if(key.toString().equalsIgnoreCase(feedback.getFeedbackId()+""))
                return feedback;  
        }  
        return null;
    }



}
