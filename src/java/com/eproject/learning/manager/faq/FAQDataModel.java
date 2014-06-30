/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.faq;

import com.eproject.learning.entity.FAQ;
import com.eproject.learning.entity.Course;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author luan
 */
public class FAQDataModel  extends ListDataModel<FAQ> implements SelectableDataModel<FAQ>, Serializable {
    public FAQDataModel() {
    }
     public FAQDataModel(List<FAQ> data) {  
        super(data);  
    }
    @Override
    public Object getRowKey(FAQ t) {
        return t.getFAQId();
    }

    @Override
    public FAQ getRowData(String key) {
        List<FAQ> faqs=(List<FAQ>) getWrappedData();
        for(FAQ faq : faqs) {  
            if(key.toString().equalsIgnoreCase(faq.getFAQId()+""))
                return faq;  
        }  
        return null;
    }



}
