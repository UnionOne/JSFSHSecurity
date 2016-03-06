package com.itibo.spring.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by union on 03.03.2016.
 */

@Entity
@Table(name = "user")
public class User {
    private Integer userId;
    private String login;
    private String password;
    private List<Role> roles;

    public User() {
    }

    public User(String login, String password, List<Role> roles) {
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}