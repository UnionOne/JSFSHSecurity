package com.itibo.service;

import com.itibo.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    void deleteUser(User user);

    User getUserByLogin(String login);

    List<User> getAllUsers();
}
