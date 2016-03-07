package com.itibo.spring.model;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;

/**
 * Created by union on 07.03.2016.
 */

@Entity
@Table(name = "User")
@ManagedBean(name = "user")
public class User {
    private int id;
    private String login;
    private String password;

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
