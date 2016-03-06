//package com.itibo.spring.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.context.FacesContext;
//
///**
// * Created by union on 03.03.2016.
// */
//
//@ManagedBean(name = "userController")
//@Component
//public class UserController {
////    public UserManager userManager;
////    private UserModel model = new UserModel();
////
////    @Autowired
////    public void setUserManager(UserManager userManager) {
////        this.userManager = userManager;
////    }
////
////    public UserModel getModel() {
////        return model;
////    }
////
////    public void setModel(UserModel model) {
////        this.model = model;
////    }
////
////    public String register() {
////        try {
////            if (!model.getPassword().equals(model.getPasswordConfirm())) {
////                throw new Exception("Passes don't match");
////            }
////            userManager.addUser(model);
////        } catch (Exception exc) {
////            FacesContext.getCurrentInstance().addMessage(null,
////                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registration Failure, " + exc.getMessage(), ""));
////            return null;
////        }
////        FacesContext.getCurrentInstance().addMessage(null,
////                new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration Success!", ""));
////        model.reset();
////        return "login";
////    }
//}