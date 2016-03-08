package com.itibo.dao;

import com.itibo.model.User;

public interface UserDAO {
    User getUserByLogin(String login);
}