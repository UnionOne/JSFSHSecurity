package com.itibo.spring.persistence;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by union on 03.03.2016.
 */

public class User {
    private int userId;
    private String login;
    private String pwd;
    private Integer enabled;
    private Set<Role> roles = new HashSet<>(0);

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}