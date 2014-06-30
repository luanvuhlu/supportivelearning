/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.manager.batch;

import com.eproject.learning.controller.CtrlCourse;
import com.eproject.learning.controller.CtrlSubject;
import com.eproject.learning.controller.CtrlUser;
import com.eproject.learning.entity.Batch;
import com.eproject.learning.entity.Course;
import com.eproject.learning.entity.Semester;
import com.eproject.learning.entity.Subject;
import com.eproject.learning.entity.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author luan
 */
public class BatchManager {
    public Map<String, Integer> getSemestersOfCourse(String courseId){
        Map<String,Integer> map=new HashMap<>();
        System.out.println("Course ID: "+courseId+"............");
        if(courseId==null || courseId.trim().equals("")){
            return map;
        }
        Set<Semester> ss=CtrlCourse.getSemestersInCourse(Integer.parseInt(courseId));
        if(ss==null){
            return map;
        }
        for(Semester s:ss){
            map.put(s.getName(),s.getSemesterId());
        } 
        return map;
    }
    public Map<String, Integer> getSemestersOfCourse(Batch batch){
        Map<String,Integer> map=new HashMap<>();
        if(batch.getCourse()==null || batch.getCourse().getCourseId()==0){
            System.out.println("Course is null");
            return map;
        }
        Set<Semester> ss=CtrlCourse.getSemestersInCourse(batch.getCourse().getCourseId());
        if(ss==null){
            return map;
        }
        for(Semester s:ss){
            map.put(s.getName(),s.getSemesterId());
        } 
        return map;
    }
    public Map<String, Integer> getSubjectsOfSemester(String semesterId){
        Map<String,Integer> map=new HashMap<>();
        if(semesterId==null || semesterId.trim().equals("")){
            return map;
        }
        Set<Subject> ss=CtrlSubject.getSubjectsInSemester(Integer.parseInt(semesterId));
        if(ss==null){
            return map;
        }
        for(Subject s:ss){
            map.put(s.getCode(), s.getSubjectId());
        } 
        return map;
    }
    public Map<String, Integer> getUsersHaveSubject(String subjectId){
        Map<String,Integer> map=new HashMap<>();
        if(subjectId==null || subjectId.trim().equals("")){
            return map;
        }
        List<User> us=CtrlUser.getUsersHaveSubject(Integer.parseInt(subjectId));
        if(us==null){
            return map;
        }
        for(User u:us){
            map.put(u.getFullName(), u.getUserId());
        } 
        return map;
    }
    public Map<String, Integer> getAllCourse(){
        Map<String,Integer> map=new HashMap<>();
        
        List<Course> cs=CtrlCourse.getAllCourse();
        if(cs==null){
            return map;
        }
        for(Course c:cs){
            map.put(c.getCode(), c.getCourseId());
        } 
        return map;
    }
}
