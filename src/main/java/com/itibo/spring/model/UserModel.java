package com.itibo.spring.model;

/**
 * Created by union on 03.03.2016.
 */

public class UserModel {
    private String login;
    private String pwd;
    private String pwdConfirm;

    public void reset() {
        this.login = null;
        this.pwd = null;
        this.pwdConfirm = null;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwdConfirm() {
        return pwdConfirm;
    }

    public void setPwdConfirm(String pwdConfirm) {
        this.pwdConfirm = pwdConfirm;
    }
}