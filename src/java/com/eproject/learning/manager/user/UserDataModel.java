/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.user;

import com.eproject.learning.manager.semester.*;
import com.eproject.learning.entity.Batch;
import com.eproject.learning.entity.User;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author luan
 */
public class UserDataModel   extends ListDataModel<User> implements SelectableDataModel<User>, Serializable {

    public UserDataModel() {
    }
     public UserDataModel(List<User> data) {  
        super(data);  
    }
    @Override
    public Object getRowKey(User t) {
        return t.getUserId();
    }

    @Override
    public User getRowData(String key) {
        List<User> users=(List<User>) getWrappedData();
        for(User user : users) {  
            if(key.toString().equalsIgnoreCase(user.getUserId()+""))
                return user;  
        }  
        return null;
    }
    
}
