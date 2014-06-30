/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author luan
 */
@ManagedBean
@ViewScoped
@SessionScoped
public class IncludeManagerController implements Serializable{
    public String path;
    public Map<String, String> includeMap=new HashMap<>();

    public IncludeManagerController() {
        initIncludeMap();
        path=includeMap.get(getAction());
    }
    
    private void initIncludeMap(){
        includeMap.put("course", "/course/course-list.xhtml");
        includeMap.put("role", "/role/role-list.xhtml");
        includeMap.put("news", "/news/news-list.xhtml");
        includeMap.put("subject", "/subject/subject-list.xhtml");
        includeMap.put("student", "/student/student-list.xhtml");
        includeMap.put("user", "/user/user-list.xhtml");
        includeMap.put("faq", "/FAQ/FAQ-list.xhtml");
        includeMap.put("batch", "/batch/batch-list.xhtml");
        includeMap.put("assignment", "/assignment/assignment-list.xhtml");
        includeMap.put("studentassignment", "/student-assignment/assignment-list.xhtml");
        includeMap.put("feedback", "/feedback/feedback-list.xhtml");
        includeMap.put("semester", "/semester/semester-list.xhtml");
        
        includeMap.put("add-student-to-batch", "/batch/add-student.xhtml");
        includeMap.put("edit-batch", "/batch/edit-batch.xhtml");
        
        includeMap.put("edit-faq", "/FAQ/edit-FAQ.xhtml");
        includeMap.put("add-faq", "/FAQ/edit-FAQ.xhtml");
        includeMap.put("view-faq", "/FAQ/FAQ-detail.xhtml");
        
        includeMap.put("edit-news", "/news/edit-news.xhtml");
        includeMap.put("add-news", "/news/edit-news.xhtml");
        includeMap.put("view-news", "/news/news-detail.xhtml");
        
        includeMap.put("edit-user", "/user/edit-user.xhtml");
        includeMap.put("add-user", "/user/edit-user.xhtml");
        
        includeMap.put("edit-student", "/student/edit-student.xhtml");
        includeMap.put("add-student", "/student/edit-student.xhtml");
        includeMap.put("view-student", "/student/student-detail.xhtml");
        
        includeMap.put("edit-course", "/course/edit-course.xhtml");
        includeMap.put("add-course", "/course/edit-course.xhtml");
        
        includeMap.put("solution", "/assignment/solution-list.xhtml");
        includeMap.put("mark", "/student-assignment/view-mark.xhtml");
        
        includeMap.put("update-assignment", "/assignment/edit-assignment.xhtml");
    }

    public static String getAction(){
        
        if(ActionBean.getParameter("action")==null){
            return "role";
        }
        if(ActionBean.getParameter("action").equalsIgnoreCase("batch"))
            if(ActionBean.getParameter("a").equalsIgnoreCase("addStudent"))
                return "add-student-to-batch";
            else if(ActionBean.getParameter("a").equalsIgnoreCase("edit") || ActionBean.getParameter("a").equalsIgnoreCase("add"))
                return "edit-batch";
        if(ActionBean.getParameter("action").equalsIgnoreCase("faq")){
            if(ActionBean.getParameter("a").equalsIgnoreCase("add"))
                return "add-faq";
            else if(ActionBean.getParameter("a").equalsIgnoreCase("edit"))
                return "edit-faq";
            else if(ActionBean.getParameter("a").equalsIgnoreCase("view"))
                return "view-faq";
        }
        if(ActionBean.getParameter("action").equalsIgnoreCase("news")){
            if(ActionBean.getParameter("a").equalsIgnoreCase("add"))
                return "add-news";
            else if(ActionBean.getParameter("a").equalsIgnoreCase("edit"))
                return "edit-news";
            else if(ActionBean.getParameter("a").equalsIgnoreCase("view"))
                return "view-news";
        }
        if(ActionBean.getParameter("action").equalsIgnoreCase("user")){
            if(ActionBean.getParameter("a").equalsIgnoreCase("add"))
                return "add-user";
            else if(ActionBean.getParameter("a").equalsIgnoreCase("edit"))
                return "edit-user";
        }
        if(ActionBean.getParameter("action").equalsIgnoreCase("student")){
            if(ActionBean.getParameter("a").equalsIgnoreCase("add"))
                return "add-student";
            else if(ActionBean.getParameter("a").equalsIgnoreCase("edit"))
                return "edit-student";
            else if(ActionBean.getParameter("a").equalsIgnoreCase("view"))
                return "view-student";
        }
        if(ActionBean.getParameter("action").equalsIgnoreCase("course")){
            if(ActionBean.getParameter("a").equalsIgnoreCase("add"))
                return "add-course";
            else if(ActionBean.getParameter("a").equalsIgnoreCase("edit"))
                return "edit-course";
        }
        if(ActionBean.getParameter("action").equalsIgnoreCase("assignment")){
            if(ActionBean.getParameter("a").equalsIgnoreCase("solution"))
                return "solution";
            if(ActionBean.getParameter("a").equalsIgnoreCase("add") || ActionBean.getParameter("a").equalsIgnoreCase("edit"))
                return "update-assignment";
        }
        return ActionBean.getParameter("action").toString();
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
