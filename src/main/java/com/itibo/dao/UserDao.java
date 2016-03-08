package com.itibo.dao;

import com.itibo.model.User;

import java.util.List;

/**
 * Created by union on 07.03.2016.
 */

public interface UserDao {
    void addUser(User user);

    void deleteUser(User user);

    List<User> listUsers();
}
