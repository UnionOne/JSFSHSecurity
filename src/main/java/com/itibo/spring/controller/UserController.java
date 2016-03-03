package com.itibo.spring.controller;

import com.itibo.spring.manger.UserManger;
import com.itibo.spring.model.UserModel;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by union on 03.03.2016.
 */

@ManagedBean
@Named
public class UserController {
    @Inject
    public UserManger userManger;
    private UserModel model = new UserModel();

    public void setUserManger(UserManger userManger) {
        this.userManger = userManger;
    }

    public UserModel getModel() {
        return model;
    }

    public void setModel(UserModel model) {
        this.model = model;
    }

    public String register() {
        try {
            if (!model.getPwd().equals(model.getPwdConfirm())) {
                throw new Exception("Passes don't macht");
            }
            userManger.addUser(model);
        } catch (Exception exc) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registration Failure, " + exc.getMessage(), ""));
            return null;
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration Success!", ""));
        model.reset();
        return "login";
    }
}