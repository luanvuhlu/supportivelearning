/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eproject.learning.manager.assignment;

import com.eproject.learning.entity.Assignment;
import com.eproject.learning.entity.Student;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author luan
 */
@ManagedBean
@SessionScoped
public class FileUploadController implements Serializable {
    
    public static boolean upload(UploadedFile file, String path) {
        System.out.println("File Uploading.....");
        if(file==null){
            System.out.println("File is null...........");
            return false;
        }
        File targetFolder = new File(path);
        if(!targetFolder.isDirectory()){
            if(!targetFolder.mkdirs()){
                System.out.println("Create "+path+" fail !");
                return false;
            }else{
                System.out.println("Folder is created !");
            }
        }
        try {
            InputStream inputStream = file.getInputstream();
            OutputStream out = new FileOutputStream(new File(targetFolder,
                    file.getFileName()));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            out.close();
            System.out.println("Upload "+path+file.getFileName()+" finish !........................");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error is here line 60 FileUploadController....................");
            return false;
        }
        return true;
    }
    public static boolean upload(UploadedFile file, Assignment assignment) {
        return upload(file, SetPathControl.getPath(assignment));
    }
    public static boolean upload(UploadedFile file, Student student, Assignment assignment) {
        return upload(file, SetPathControl.getPath(student, assignment));
    }
}
