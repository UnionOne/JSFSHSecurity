package com.itibo.spring.manager;

import com.itibo.spring.dao.UserDAO;
import com.itibo.spring.model.UserModel;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by union on 03.03.2016.
 */

@SuppressWarnings("ALL")
@Named
public class UserManger {
    @Inject
    public UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void addUser(UserModel model) {
        userDAO.addUser((model));
    }
}