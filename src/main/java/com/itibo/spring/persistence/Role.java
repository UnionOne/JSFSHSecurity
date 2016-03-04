package com.itibo.spring.persistence;

import javax.persistence.*;

/**
 * Created by union on 03.03.2016.
 */

@Entity
@Table(name = "ROLE")
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @Column(name = "code")
    private String code;

    @Column(name = "label")
    private String label;

    @Column(name = "user_id")
    private User user;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}