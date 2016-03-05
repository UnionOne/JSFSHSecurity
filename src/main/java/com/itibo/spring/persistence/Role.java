package com.itibo.spring.persistence;

import javax.persistence.*;

/**
 * Created by union on 03.03.2016.
 */

@Entity
@Table(name = "role", schema = "security")
public class Role {
    private Integer roleId;
    private User user;
    private String code;

    public Role() {
    }

    public Role(User user, String code) {
        this.user = user;
        this.code = code;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (roleId != null ? !roleId.equals(role.roleId) : role.roleId != null) return false;
        if (user != null ? !user.equals(role.user) : role.user != null) return false;
        return code != null ? code.equals(role.code) : role.code == null;

    }

    @Override
    public int hashCode() {
        int result = roleId != null ? roleId.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }
}