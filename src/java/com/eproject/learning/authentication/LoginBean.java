/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eproject.learning.authentication;

import com.eproject.learning.controller.CtrlAccount;
import com.eproject.learning.entity.Role;
import com.eproject.learning.entity.Student;
import com.eproject.learning.entity.User;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext; 

/**
 *
 * @author luan
 */
@ManagedBean
@ViewScoped
@SessionScoped
public class LoginBean implements Serializable{ 
    private String username;  
       
    private String password;  
     
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
      
    public String getUsername() {  
        return username;  
    }  
  
    public void setUsername(String username) {  
        this.username = username;  
    }  
  
    public String getPassword() {  
        return password;  
    }  
  
    public void setPassword(String password) {  
        this.password = password;  
    }  
  
    public void login(ActionEvent actionEvent) throws IOException {  
        RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean loggedIn = false;
        Object account=CtrlAccount.getAccount(username, password);
        FacesContext fContext=FacesContext.getCurrentInstance();
        HttpSession session=(HttpSession)fContext.getExternalContext().getSession(true);
        if(account==null){
            loggedIn = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid username or password. Plesase try again !");
        }else{
            
        
            if(account.getClass()==User.class){
                User user=(User)account;
                if(user.isStatus()){
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            "Login error", "This account is blocked"));
                return;
                }
                session.setAttribute("user", user.getUserId());
                session.setAttribute("role", "admin");
                if(user.getRoles()!=null && user.getRoles().size() >1)
                    context.addCallbackParam("role", "user");
                else{
                    if(user.getRoles()!=null){
                    for(Role r:user.getRoles()){
                        session.setAttribute("role", r.getName());
                        break;
                    }
                    }
                }
            }else if(account.getClass()==Student.class){
                Student student=(Student)account;
                if(student.isStatus()){
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            "Login error", "This account is blocked"));
            return;
                }
                
                session.setAttribute("student", student.getStudentId());
            }
            loggedIn = true;  
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login info", "Welcome "+username);
            context.addCallbackParam("loggedIn", loggedIn);
        }
        fContext.addMessage(null, msg);
          
    }
    public void logout(ActionEvent actionEvent){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    public void setRoleAccount(ActionEvent actionEvent){
        if(role==null || role ==""){
            return;
        }
       FacesContext fContext=FacesContext.getCurrentInstance();
       HttpSession session=(HttpSession)fContext.getExternalContext().getSession(true); 
       session.setAttribute("role", role);
       
    }
}
