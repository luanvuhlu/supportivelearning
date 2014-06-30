///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package com.eproject.learning.view;
//
//import java.util.HashMap;
//import java.util.Map;
//import javax.faces.bean.ManagedBean;
//import javax.faces.context.FacesContext;
//import javax.servlet.http.HttpSession;
//
///**
// *
// * @author luan
// */
//@ManagedBean
//public class MenuTopBean {
//   private Map<String, String> valueOutcome;
//   
//
//    public MenuTopBean() {
//        FacesContext fContext=FacesContext.getCurrentInstance();
//        HttpSession session=(HttpSession)fContext.getExternalContext().getSession(true);
//        
//        valueOutcome=new HashMap<>();
//        valueOutcome.put("Trang chủ", "index");
//        valueOutcome.put("Khóa học", "courses");
//        
//        if(session.getAttribute("student") !=null && session.getAttribute("student").toString() !="0"){
//            valueOutcome.put("Học tập", "manager");
//        }
//        
//        if(session.getAttribute("user") !=null && session.getAttribute("user").toString() !="0"){
//            if(session.getAttribute("role") !=null && session.getAttribute("user").toString() !=""){
//                if(session.getAttribute("role").toString().equalsIgnoreCase("admin")){
//                    valueOutcome.put("Quản trị", "manager");
//                }else{
//                    valueOutcome.put("Quản lý giảng dạy", "manager");
//                }
//            }
//        }
//        valueOutcome.put("Tin tức", "view-news");
//        valueOutcome.put("Liên hệ", "contact");
//        valueOutcome.put("Giới thiệu", "about");
//    }
//
//    public MenuTopBean(Map<String, String> setValueOutcome) {
//        this.valueOutcome = valueOutcome;
//    }
//
//    public Map<String, String> getValueOutcome() {
//        return valueOutcome;
//    }
//    
//    public Object[] getValues(){
//        return valueOutcome.keySet().toArray();
//    }
//    public String getOutcome(String value){
//        return valueOutcome.get(value);
//    }
//}
