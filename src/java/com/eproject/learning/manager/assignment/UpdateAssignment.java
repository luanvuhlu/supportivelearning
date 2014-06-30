/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.assignment;

import com.eproject.learning.authentication.Authentication;
import com.eproject.learning.controller.CtrlAssignment;
import com.eproject.learning.controller.CtrlBatch;
import com.eproject.learning.entity.Assignment;
import com.eproject.learning.entity.Batch;
import com.eproject.learning.view.ActionBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author luan
 */
@ManagedBean
@ViewScoped
public class UpdateAssignment implements Serializable{
    private Assignment assignment;
    private boolean addMode=false;
    private UploadedFile file;
//    private Batch batch;
    private final String dialogSelectBatchPath="select-batch.xhtml";

    public UpdateAssignment() {
       String assignmentPara=ActionBean.getParameter("ass");
       
        if(ActionBean.getParameter("a").equals("add")){
            System.out.println("Add");
            addMode=true;
            assignment=new Assignment();
            Batch batch=new Batch();
            batch.setName("Select");
            assignment.setBatch(batch);
        }else if(assignmentPara.equalsIgnoreCase("")){
            return;
        }else {
            assignment=CtrlAssignment.getById(Integer.parseInt(assignmentPara));
            
        }
    }
//    public void handleFileUpload() {
//        file=event.getFile();
//        assignment.setFilePath(file.getFileName());
//        if(upload()){
//            assignment.setFilePath(file.getFileName());
//            save();
//        }else{
//            RequestContext context = RequestContext.getCurrentInstance();
//            String msg = "Upload file error !";
//            String title = "Update Assignment";
//            FacesMessage fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
//            FacesContext.getCurrentInstance().addMessage(null, fMsg);
//        }
//    }
public boolean upload() {
    
    try{
        if(file==null){
            if(!assignment.getFilePath().trim().equals("")){
                return true;
            }
        }
        assignment.setFilePath(file.getFileName());
        return FileUploadController.upload(file, assignment);
    }catch(NullPointerException ex){
        ex.printStackTrace();
        return false;
    }
    }
 public void filUploadListener(FileUploadEvent event) {
        file = event.getFile();
 }

public StreamedContent download(Assignment assignment){
        return FileDownloadController.download(assignment);
    }
    public void save(){
        boolean success = false;
        RequestContext context = RequestContext.getCurrentInstance();
        String msg = "";
        String title = "";
        FacesMessage fMsg = null;
        String result = "";
        assignment.setSubject(assignment.getBatch().getSubject());
        assignment.setUser(Authentication.getUser());
        if (assignment == null) {
            msg = "Error";
            fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        } else if(assignment.getBatch()==null || assignment.getBatch().getBatchId()==null){
            msg="Batch is required !";
            fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        }else if(!upload()){
            msg="File is required !";
            fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        }else if(assignment.getStartDate().getTime() >=assignment.getEndDate().getTime()){
            msg="Start Date must smaller than End date !";
            fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        }else if(assignment.getEndDate().getTime() <=(new Date()).getTime()){
            msg="End Date must greater than now !";
            fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        }
        else {
            if (assignment.getAssignmentId() == 0) {
                title = "Add Assignment";
                assignment.setSubject(assignment.getBatch().getSubject());
                result = CtrlAssignment.add(assignment);
            } else {
                title = "Edit Assignment";
                result = CtrlAssignment.update(assignment);
            }
            if (result.equals(CtrlAssignment.SUCCESS)) {
                msg = "Success";
                fMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg);
                success = true;
                reset();
            } else {
                if (result.equals(CtrlAssignment.ERROR)) {
                    msg = "Error";
                    fMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
                }
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, fMsg);
        context.addCallbackParam("update", success);
        
 
    }
    public void reset(){
        if(addMode){
            assignment=new Assignment();
            return;
        }
        assignment=CtrlAssignment.getById(assignment.getAssignmentId());
    }

    public List<Batch> getBatchs(){
        return CtrlBatch.getAll();
    }
    public List<Assignment> getUnfinishAssignment(Batch batch){
        List<Assignment> assignments=new ArrayList<>();
        for(Assignment a:batch.getAssignments()){
            if(!a.isStatus())
                assignments.add(a);
        }
        return assignments;
    }
    public void onBatchSelect(SelectEvent event){
        Batch batch=(Batch)event.getObject();
        System.out.println("Selected Batch"+batch.getName()+".............");
        assignment.setBatch(batch);
    }
    
    public Assignment getAssignment() {
        return assignment;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setAddMode(boolean addMode) {
        this.addMode = addMode;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public boolean isAddMode() {
        return addMode;
    }

    public String getDialogSelectBatchPath() {
        return dialogSelectBatchPath;
    }

  

   

   
   
}
