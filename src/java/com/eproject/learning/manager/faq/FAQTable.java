/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.faq;

import com.eproject.learning.controller.CtrlFAQ;
import com.eproject.learning.entity.FAQ;
import com.eproject.learning.manager.faq.FAQDataModel;
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
public class FAQTable  implements Serializable{
    private boolean disable = true;
    private List<FAQ> FAQs=CtrlFAQ.getAllFAQ();
    private FAQ[] selectedFAQs;
    private FAQDataModel mediumFAQsModel;

    public FAQTable() {
        mediumFAQsModel=new FAQDataModel(FAQs);
    }

    public void delete(){
        String result = "";
        List<FAQ> failLs = new ArrayList<>();
        FacesMessage message = null;
        for (FAQ faq : selectedFAQs) {
            result = CtrlFAQ.delete(faq.getFAQId());
            if (result.equals(CtrlFAQ.ERROR)) {
                failLs.add(faq);
            }
        }
        if (failLs.size() > 0) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete error", failLs.toString() + " are'nt deleted.\nPlease try again !");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Infomation", "Delete success");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        updateFAQList();
    }
    private void updateFAQList() {
        mediumFAQsModel = new FAQDataModel(CtrlFAQ.getAllFAQ());
    }
    public void select() {
        if (selectedFAQs == null || selectedFAQs.length < 1) {
            disable = true;
        } else {
            disable = false;
        }
    }
    public void changeStatus(){
        
    }
    
    public List<FAQ> getFAQs() {
        return FAQs;
    }

    public FAQDataModel getMediumFAQsModel() {
        return mediumFAQsModel;
    }

    public FAQ[] getSelectedFAQs() {
        return selectedFAQs;
    }


    public void setFAQs(List<FAQ> FAQs) {
        this.FAQs = FAQs;
    }

    public void setMediumFAQsModel(FAQDataModel mediumFAQsModel) {
        this.mediumFAQsModel = mediumFAQsModel;
    }


    public void setSelectedFAQs(FAQ[] selectedFAQs) {
        this.selectedFAQs = selectedFAQs;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public boolean isDisable() {
        return disable;
    }
    
   
}
