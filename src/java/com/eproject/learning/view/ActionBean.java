/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luan
 */
public class ActionBean {
    public static String getParameter(String paraName){
        HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();   
        if(req.getParameter(paraName) ==null || req.getParameter(paraName).isEmpty()){
            return "";
        }
        return req.getParameter(paraName);
    }
    public static void redirect(String location){
        HttpServletResponse res=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            res.sendRedirect(location);
        } catch (IOException ex) {
            Logger.getLogger(ActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
