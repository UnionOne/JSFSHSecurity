package com.itibo.model;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;

/**
 * Created by union on 07.03.2016.
 */

@Entity
@Table(name = "users")
@ManagedBean(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    private String login;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Role role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

