/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.news;


import com.eproject.learning.entity.News;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author luan
 */
public class NewsDataModel  extends ListDataModel<News> implements SelectableDataModel<News>, Serializable {
    public NewsDataModel() {
    }
     public NewsDataModel(List<News> data) {  
        super(data);  
    }
    @Override
    public Object getRowKey(News t) {
        return t.getNewsId();
    }

    @Override
    public News getRowData(String key) {
        List<News> newss=(List<News>) getWrappedData();
        for(News news : newss) {  
            if(key.toString().equalsIgnoreCase(news.getNewsId()+""))
                return news;  
        }  
        return null;
    }



}
