/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.feedback;

import com.eproject.learning.manager.feedback.*;
import com.eproject.learning.manager.feedback.*;
import com.eproject.learning.controller.CtrlFeedback;
import com.eproject.learning.entity.Feedback;
import com.eproject.learning.manager.feedback.FeedbackDataModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@SessionScoped
public class FeedbackTable implements Serializable{
    private boolean disable = true;
    private List<Feedback> feedbacks=CtrlFeedback.getAllFeedback();
    private Feedback currentFeedback;
    private Feedback[] selectedFeedbacks;
    private FeedbackDataModel mediumFeedbacksModel;
    private final String dialogViewPath="feedback-detail.xhtml";

    public FeedbackTable() { 
        mediumFeedbacksModel=new FeedbackDataModel(feedbacks);
        currentFeedback=new Feedback();
    }
    
   
    public void showDetail(Feedback feedback){
        currentFeedback=feedback;
    }
    public void delete(Feedback feedback){
        FacesMessage message = null;
        String result="";
        if(feedback==null){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete error", "Can't found feedback !");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return;
        }
        result = CtrlFeedback.delete(feedback.getFeedbackId());
        if(result.equals(CtrlFeedback.SUCCESS)){
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Infomation", "Delete success");
            FacesContext.getCurrentInstance().addMessage(null, message);
            updateFeedbackList();
        }else{
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete error", "Can't delete feedback !");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
    }
    public void delete(){
        String result = "";
        List<Feedback> failLs = new ArrayList<>();
        FacesMessage message = null;
        for (Feedback feedback : selectedFeedbacks) {
            result = CtrlFeedback.delete(feedback.getFeedbackId());
            if (result.equals(CtrlFeedback.ERROR)) {
                failLs.add(feedback);
            }
        }
        if (failLs.size() > 0) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete error", failLs.toString() + " are'nt deleted.\nPlease try again !");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Infomation", "Delete success");
            FacesContext.getCurrentInstance().addMessage(null, message);
            updateFeedbackList();
        }

        
    }
    private void updateFeedbackList() {
        mediumFeedbacksModel = new FeedbackDataModel(CtrlFeedback.getAllFeedback());
    }
    public void select() {
        if (selectedFeedbacks == null || selectedFeedbacks.length < 1) {
            disable = true;
        } else {
            disable = false;
        }
    }
    public void changeStatus(){
        
    }
    
    public Feedback getCurrentFeedback() {
        return currentFeedback;
    }



    public String getDialogViewPath() {
        return dialogViewPath;
    }

   
    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public FeedbackDataModel getMediumFeedbacksModel() {
        return mediumFeedbacksModel;
    }

    
    public Feedback[] getSelectedFeedbacks() {
        return selectedFeedbacks;
    }

    public void setCurrentFeedback(Feedback currentFeedback) {
        this.currentFeedback = currentFeedback;
    }



    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public void setMediumFeedbacksModel(FeedbackDataModel mediumFeedbacksModel) {
        this.mediumFeedbacksModel = mediumFeedbacksModel;
    }



    public void setSelectedFeedbacks(Feedback[] selectedFeedbacks) {
        this.selectedFeedbacks = selectedFeedbacks;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public boolean isDisable() {
        return disable;
    }
    
   
}
