/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.role;


import com.eproject.learning.manager.role.*;
import com.eproject.learning.entity.Role;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author luan
 */
public class RoleDataModel  extends ListDataModel<Role> implements SelectableDataModel<Role>, Serializable {
    public RoleDataModel() {
    }
     public RoleDataModel(List<Role> data) {  
        super(data);  
    }
    @Override
    public Object getRowKey(Role t) {
        return t.getRoleId();
    }

    @Override
    public Role getRowData(String key) {
        List<Role> Roles=(List<Role>) getWrappedData();
        for(Role r : Roles) {  
            if(key.toString().equalsIgnoreCase(r.getRoleId()+""))
                return r;  
        }  
        return null;
    }



}
