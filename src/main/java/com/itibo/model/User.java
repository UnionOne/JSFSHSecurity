package com.itibo.model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String login;
    private String password;
    private boolean enabled;
    private Set<UserRole> userRole = new HashSet<>(0);

    public User() {

    }

    public User(String login, String password, boolean enabled) {
        this.login = login;
        this.password = password;
        this.enabled = enabled;
    }

    public User(String login, String password, boolean enabled, Set<UserRole> userRole) {
        this.login = login;
        this.password = password;
        this.enabled = enabled;
        this.userRole = userRole;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }
}
