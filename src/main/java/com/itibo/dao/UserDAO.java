package com.itibo.dao;

import com.itibo.model.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user);

    void deleteUser(User user);

    User getUserByLogin(String login);

    List<User> getAllUsers();
}