package com.itibo.service;

import com.itibo.model.User;

import java.util.List;

/**
 * Created by union on 07.03.2016.
 */

public interface UserService {
    public void addUser(User user);

    public void deleteUser(User user);

    public User getUserByLogin(String login);

    public List<User> listUsers();
}