/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.faq;

import com.eproject.learning.controller.CtrlFAQ;
import com.eproject.learning.entity.FAQ;
import com.eproject.learning.view.ActionBean;
import java.io.Serializable;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author luan
 */
@ManagedBean
@ViewScoped
@SessionScoped
public class ViewFAQ implements Serializable{
    private FAQ fAQ;
    public ViewFAQ() {
        String faqPara=ActionBean.getParameter("f");
        if(faqPara.equalsIgnoreCase("")){
            ActionBean.redirect("manager.xhtml?action=faq");
            return;
        }else {
            fAQ=CtrlFAQ.getById(Integer.parseInt(faqPara));
        }
    }
    public FAQ getfAQ() {
        return fAQ;
    }

    public void setfAQ(FAQ fAQ) {
        this.fAQ = fAQ;
    }
    
   
}
