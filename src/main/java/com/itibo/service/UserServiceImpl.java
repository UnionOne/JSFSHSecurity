package com.itibo.service;

import com.itibo.dao.UserDAO;
import com.itibo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

/**
 * Created by union on 07.03.2016.
 */

@Service
@Transactional
@ManagedBean(name = "userService")
@SessionScoped
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDao;

    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }

    public void addUser(User user) {
        this.userDao.addUser(user);
    }

    public void deleteUser(User user) {
        this.userDao.deleteUser(user);
    }

    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    public List<User> listUsers() {
        return this.userDao.listUsers();
    }
}
