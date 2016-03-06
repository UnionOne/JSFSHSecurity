package com.itibo.spring.dao;

import com.itibo.spring.model.UserModel;
import com.itibo.spring.model.Role;
import com.itibo.spring.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by union on 03.03.2016.
 */

@Component
@Transactional("transactionManager")
public class UserDAOImpl implements UserDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addUser(UserModel model) {
        User user = new User();
        user.setLogin(model.getLogin());
        user.setPassword(model.getPassword());
        Role role = (Role) sessionFactory.getCurrentSession()
                .createQuery("from Role where code='ROLE_USER'").uniqueResult();

        if (role == null) {
            role = new Role();
            role.setCode("ROLE_USER");
            sessionFactory.getCurrentSession().save(role);
        }
        List<Role> roles = new LinkedList<>();
        roles.add(role);
        user.setRoles(roles);
        sessionFactory.getCurrentSession().save(user);
    }
}