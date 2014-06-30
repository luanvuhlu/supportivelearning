///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package com.eproject.learning.view;
//
//import java.io.Serializable;
//import java.util.HashMap;
//import java.util.Map;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
//import javax.faces.bean.ViewScoped;
//
///**
// *
// * @author luan
// */
//@ManagedBean
//@ViewScoped
//@SessionScoped
//public class MenuManager implements Serializable{
//    public final String menuLayout="/menu/menu-layout.xhtml";
//    public final String menuCategory="/menu/manager-category.xhtml";
//    public String menuAction;
//    public Map<String, String> menuActionMap=new HashMap<>();
//    public Map<String, String> menuManagerMap=new HashMap<>();
//    
//
//    public MenuManager() {
//        initMenuActionMap();
//        menuAction=menuActionMap.get(IncludeManagerController.getAction());
//    }
//    private void initMenuActionMap(){
//        menuActionMap.put("course", "/menu/manager-course.xhtml");
//        menuActionMap.put("role", "/menu/manager-role.xhtml");
//        menuActionMap.put("news", "/menu/manager-news.xhtml");
//        menuActionMap.put("subject", "/menu/manager-subject.xhtml");
//        menuActionMap.put("student", "/menu/manager-student.xhtml");
//        menuActionMap.put("user", "/menu/manager-user.xhtml");
//        menuActionMap.put("FAQ", "/menu/manager-FAQ.xhtml");
//        menuActionMap.put("batch", "/menu/manager-batch.xhtml");
//        menuActionMap.put("assignment", "/menu/manager-assignment.xhtml");
//        menuActionMap.put("semester", "/menu/manager-semester.xhtml");
//        menuActionMap.put("feedback", "/menu/manager-feedback.xhtml");
//    }
//    private void initMenuManagerMap(){
//        menuManagerMap.put("Course", "/menu/manager-course.xhtml");
//    }
//
//    public String getMenuCategory() {
//        return menuCategory;
//    }
//    
//    public String getMenuLayout() {
//        return menuLayout;
//    }
//
//    public void setMenuAction(String menuAction) {
//        this.menuAction = menuAction;
//    }
//
//    public String getMenuAction() {
//        return menuAction;
//    }
//
//    public Map<String, String> getMenuActionMap() {
//        return menuActionMap;
//    }
//    
//    
//}
