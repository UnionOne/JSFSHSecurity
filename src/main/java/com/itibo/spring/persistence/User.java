package com.itibo.spring.persistence;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by union on 03.03.2016.
 */

@Entity
@Table(name = "USER")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "login")
    private String login;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "enabled")
    private Integer enabled;

    @OneToMany(mappedBy = "role_id")
    private Set<Role> roles = new HashSet<>(0);

    public User(String login, String pwd, Integer enabled, Set<Role> roles) {
        this.login = login;
        this.pwd = pwd;
        this.enabled = enabled;
        this.roles = roles;
    }

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