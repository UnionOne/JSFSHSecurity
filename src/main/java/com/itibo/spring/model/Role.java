package com.itibo.spring.model;

import javax.persistence.*;

/**
 * Created by union on 07.03.2016.
 */

@Entity
@Table(name = "Role")
public class Role {
    private int id;
    private String code;

    public Role() {
    }

    public Role(String code) {
        this.code = code;
    }

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
