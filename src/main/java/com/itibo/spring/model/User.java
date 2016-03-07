package com.itibo.spring.model;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    private Set<Role> userRoles = new HashSet<>(0);

    public User() {

    }

    public User(String login, String password, Set<Role> userRoles) {
        this.login = login;
        this.password = password;
        this.userRoles = userRoles;
    }

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "User_Role", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    public Set<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<Role> userRoles) {
        this.userRoles = userRoles;
    }
}
