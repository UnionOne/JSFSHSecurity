package com.itibo.spring.service;

import com.itibo.spring.model.User;

import java.util.List;

/**
 * Created by union on 07.03.2016.
 */

public interface UserService {
    void addUser(User user);

    void deleteUser(User user);

    List<User> listUsers();
}
