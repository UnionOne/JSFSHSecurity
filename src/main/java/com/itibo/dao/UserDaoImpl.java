package com.itibo.dao;

import com.itibo.model.Role;
import com.itibo.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by union on 07.03.2016.
 */

@Repository
public class UserDAOImpl implements UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
        Role role = new Role();
        role.setRole("user");
        user.setRole(role);
        session.persist(user);
        logger.info("User saved successfully, User details: " + user.toString());
    }

    @Override
    public void deleteUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(user);
        logger.info("User delete successfully, User details: " + user.toString());
    }

    @SuppressWarnings("unchecked")
    public User getUserByLogin(String login) {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = new LinkedList<>();
        Query query = session.createQuery("from User u where u.login = :login");
        query.setParameter("login", login);
        userList = query.list();
        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> listUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from User").list();
        for (User user : userList) {
            logger.info("User List:" + user.toString());
        }
        return userList;
    }
}
