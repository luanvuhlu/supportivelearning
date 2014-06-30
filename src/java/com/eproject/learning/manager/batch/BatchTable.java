/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.batch;

import com.eproject.learning.authentication.Authentication;
import com.eproject.learning.controller.CtrlAssignment;
import com.eproject.learning.controller.CtrlBatch;
import com.eproject.learning.controller.CtrlCourse;
import com.eproject.learning.controller.CtrlSemester;
import com.eproject.learning.controller.CtrlSubject;
import com.eproject.learning.controller.CtrlUser;
import com.eproject.learning.entity.Assignment;
import com.eproject.learning.entity.Batch;
import com.eproject.learning.entity.Course;
import com.eproject.learning.entity.Semester;
import com.eproject.learning.entity.Subject;
import com.eproject.learning.entity.User;
import com.eproject.learning.manager.assignment.AssignmentDataModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class BatchTable implements Serializable{
    private boolean disable = true;
    private List<Batch> batchs=CtrlBatch.getAll();
    private Batch currentBatch;
    private Batch[] selectedBatchs;
    private BatchDataModel mediumBatchsModel;
    private final String dialogViewPath="batch-detail.xhtml";

    public BatchTable() {
        mediumBatchsModel=new BatchDataModel(batchs);
        currentBatch=new Batch();
    }
    public void delete() {
        String result = "";
        List<Batch> failLs = new ArrayList<>();
        FacesMessage message = null;
        for (Batch batch : selectedBatchs) {
            if(batch.getStudents() !=null && batch.getStudents().size() >0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete error", "Batch "+batch.getName()+" has some students. Please delete students before !");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                return;
            }
            if(batch.getAssignments()!=null && batch.getAssignments().size() >0){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete error", "Batch "+batch.getName()+" has some assignments. Please delete assignments before !");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                return;
            }
            result = CtrlBatch.delete(batch.getBatchId());
            if (result.equals(CtrlBatch.ERROR)) {
                failLs.add(batch);
            }
        }
        if (failLs.size() > 0) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete error", failLs.toString() + " are'nt deleted.\nPlease try again !");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Infomation", "Delete success");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        updateBatchList();
    }

    private void updateBatchList() {
        mediumBatchsModel = new BatchDataModel(CtrlBatch.getAll());
    }
    public boolean isStudying(Batch batch){
        User user=Authentication.getUser();
        if(user==null){
            return false;
        }
        if(batch.getUser().getUserId().equals(user.getUserId())){
            return true;
        }
        return false;
    }

    public void select() {
        if (selectedBatchs == null || selectedBatchs.length < 1) {
            disable = true;
        } else {
            disable = false;
        }
    }
    public void changeStatus(){
        
    }
    public void showDetail(Batch batch){
        currentBatch=batch;
    }
    
   
    public List<Batch> getBatchs() {
        return batchs;
    }

    public BatchDataModel getMediumBatchsModel() {
        return mediumBatchsModel;
    }

   

    public Batch[] getSelectedBatchs() {
        return selectedBatchs;
    }

    public void setBatchs(List<Batch> batchs) {
        this.batchs = batchs;
    }

    public void setMediumBatchsModel(BatchDataModel mediumBatchsModel) {
        this.mediumBatchsModel = mediumBatchsModel;
    }

   

    public void setSelectedBatchs(Batch[] selectedBatchs) {
        this.selectedBatchs = selectedBatchs;
    }

    public Batch getCurrentBatch() {
        return currentBatch;
    }

    public String getDialogViewPath() {
        return dialogViewPath;
    }

    public void setCurrentBatch(Batch currentBatch) {
        this.currentBatch = currentBatch;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public boolean isDisable() {
        return disable;
    }
    

}
