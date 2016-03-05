package com.itibo.spring.model;

/**
 * Created by union on 03.03.2016.
 */

public class UserModel {
    private String login;
    private String password;
    private String passwordConfirm;

    public void reset() {
        this.login = null;
        this.password = null;
        this.passwordConfirm = null;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}