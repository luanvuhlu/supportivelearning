/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.assignment;

import com.eproject.learning.controller.CtrlAbs;
import com.eproject.learning.controller.CtrlAssignment;
import com.eproject.learning.entity.Assignment;
import com.eproject.learning.entity.MarkAssignment;
import com.eproject.learning.entity.Student;

/**
 *
 * @author luan
 */
public class SetPathControl {
    public static final String ROOT="Assignment/";
    public static String getPath(Assignment assignment){
        String path=ROOT;
        path+=assignment.getBatch().getName();
        path+="/";
        path+=assignment.getSubject().getCode();
        path+="/";
        return path;
    }
    public static String getPath(MarkAssignment markAssignment){
        String path=getPath(markAssignment.getAssignment());
        path+=markAssignment.getStudent().getRoll();
        path+="/";
        return path;
    }
    public static String getPath(Student student, Assignment assignment){
        String path=ROOT;
        path+=getPath(assignment);
        path+=student.getRoll();
        path+="/";
        return path;
    }
}
