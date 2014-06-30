/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eproject.learning.manager.feedback;

import com.eproject.learning.authentication.Authentication;
import com.eproject.learning.controller.CtrlFeedback;
import com.eproject.learning.controller.CtrlNews;
import com.eproject.learning.entity.Feedback;
import com.eproject.learning.entity.Student;
import com.sun.xml.ws.security.impl.policy.Constants;
import java.io.Serializable;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author luan
 */
@ManagedBean
@ViewScoped
public class SendFeedback implements  Serializable{

    private String title;
    private String content;

    public SendFeedback() {

    }
    
    public boolean isStudent(){
        try{
        Student student=(Student)new Authentication().getAccount();
        if(student==null){
            return false;
        }
        return true;
        }catch(Exception ex){
            return false;
        }
    }

    public void save() {
        RequestContext context = RequestContext.getCurrentInstance();
        String msg = "";
        String title = "";
        FacesMessage fMsg = null;
        String result = "";
        Student s = Authentication.getStudent();
        if (s == null || s.getStudentId() == 0) {
            msg="You must login to send feedback";
            fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
            
        }else{
            Feedback f = new Feedback();
            f.setTitle(this.title);
            f.setContent(content);
            f.setDate(new Date());
            f.setStudent(s);
            result = CtrlFeedback.add(f);
            if (result.equals(CtrlFeedback.SUCCESS)) {
                msg = "Success";
                fMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg);
                this.title="";
                content="";
            } else {
                if (result.equals(CtrlFeedback.ERROR)) {
                    msg = "Error";
                    fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
                }
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, fMsg);
    }
    public void showDialog(){
        RequestContext.getCurrentInstance().openDialog("feedback-dialog.xhtml");
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
