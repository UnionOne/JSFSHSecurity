package com.itibo.spring.manager;

import com.itibo.spring.dao.UserDAO;
import com.itibo.spring.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by union on 03.03.2016.
 */

@Component
public class UserManager {
    public UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void addUser(UserModel model) {
        userDAO.addUser((model));
    }
}