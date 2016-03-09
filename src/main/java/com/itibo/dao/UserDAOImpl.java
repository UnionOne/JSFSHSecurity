package com.itibo.dao;

import com.itibo.model.Role;
import com.itibo.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// TODO Generic DAO for models

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        //Set<Role> roles = new HashSet<>();
        //Role role = new Role();
        //role.setRole("ROLE_ADMIN");
        //roles.add(role);
        //user.setRoles(roles);
        session.persist(user);
    }

    @Override
    public void deleteUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(user);
    }

    @Override
    public User getUserByLogin(String login) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User").list();
    }
}