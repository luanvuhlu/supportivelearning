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
public class UpdateFAQ implements Serializable{
    private FAQ fAQ;
    private boolean addMode=false;
    public UpdateFAQ() {
        String faqPara=ActionBean.getParameter("f");
        if(ActionBean.getParameter("a").equals("add")){
            System.out.println("Add");
            addMode=true;
            fAQ=new FAQ();
        }else if(faqPara.equalsIgnoreCase("")){
            ActionBean.redirect("manager.xhtml?action=faq");
            return;
        }else {
            fAQ=CtrlFAQ.getById(Integer.parseInt(faqPara));
        }
    }
    public void save(){
        String msg = "";
        String title="";
        FacesMessage fMsg = null;
        String result="";
        if(fAQ.getFAQId()==0){
            title="Add FAQ";
            fAQ.setDate(new Date());
            result=CtrlFAQ.add(fAQ);
        }else{
            title="Update FAQ";
            result=CtrlFAQ.update(fAQ);
        }
        if (result == CtrlFAQ.ERROR) {
            msg = "Error";
            fMsg = new FacesMessage(title, msg);
        } else {
            msg = "Success";
            fMsg = new FacesMessage(title, msg);
            fAQ=new FAQ();
        }
        FacesContext.getCurrentInstance().addMessage(null, fMsg);
    }
    public void reset(){
        if(addMode){
            fAQ=new FAQ();
            return;
        }
        fAQ=CtrlFAQ.getById(fAQ.getFAQId());
        
    }

    public FAQ getfAQ() {
        return fAQ;
    }

    public void setfAQ(FAQ fAQ) {
        this.fAQ = fAQ;
    }
    
   
}
