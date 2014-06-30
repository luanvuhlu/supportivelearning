/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.assignment;

import com.eproject.learning.controller.CtrlMark;
import com.eproject.learning.entity.Assignment;
import com.eproject.learning.entity.MarkAssignment;
import com.eproject.learning.entity.Student;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author luan
 */
@ManagedBean
public class FileDownloadController implements Serializable{
    
    public static StreamedContent download(String fileName, String path, String outFileName){
        DefaultStreamedContent file=null;
        try{
        InputStream stream = new FileInputStream(new File(path+fileName));
        file = new DefaultStreamedContent(stream);
        file.setName(outFileName);
        }catch(Exception ex){
            System.out.println(fileName+ " not found !");
        }
        return file;
    }
    public static StreamedContent download(Assignment assignment){
        return download(assignment.getFilePath(), SetPathControl.getPath(assignment), assignment.getName());
    }
    public static StreamedContent download(Assignment assignment, Student student){
        MarkAssignment markAssignment=CtrlMark.getMarkAssignment(student, assignment);
        if(markAssignment==null){
            System.out.println("Mark is null here..................");
        }
        return download(markAssignment);
    }
    public static StreamedContent download(MarkAssignment markAssignment){
        
        return download(markAssignment.getFilePath(), SetPathControl.getPath(markAssignment), markAssignment.getStudent().getRoll());
    }
}
