/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eproject.learning.view;

import com.eproject.learning.entity.Role;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author luan
 */
//public class RoleConvert {
//    
//}
@FacesConverter("RoleConvert")
public class RoleConvert implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
         Role bean = new Role();
         bean.setRoleId(Integer.parseInt(arg2));
      return bean;
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        return String.valueOf(((Role) arg2).getRoleId());
    }

}
