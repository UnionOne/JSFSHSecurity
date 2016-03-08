package com.itibo.service;

import com.itibo.dao.RoleDAO;
import com.itibo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by union on 08.03.2016.
 */

@Service
@Transactional
public class RoleServiceImpl {
    @Autowired
    private RoleDAO roleDAO;

    public Role getRoleById(int id) {
        return roleDAO.getRoleById(id);
    }
}