//package com.itibo.service;
//
//import com.itibo.dao.UserDAO;
//import com.itibo.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
///**
// * Created by union on 08.03.2016.
// */
//
//@Service
//@Transactional(readOnly = true)
//public class CustomUserDetailsService implements UserDetailsService {
//    @Autowired
//    private UserDAO userDao;
//
//    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        User domainUser = userDao.getUserByLogin(login);
//
//        return new org.springframework.security.core.userdetails.User(
//                domainUser.getLogin(),
//                domainUser.getPassword(),
//                true, true, true, true,
//                getAuthorities(domainUser.getRole().getId())
//        );
//    }
//
//    public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
//        return getGrantedAuthorities(getRoles(role));
//    }
//
//    public List<String> getRoles(Integer role) {
//        List<String> roles = new ArrayList<>();
//
//        if (role == 1) {
//            roles.add("ROLE_USER");
//            roles.add("ROLE_ADMIN");
//        } else if (role == 2) {
//            roles.add("ROLE_USER");
//        }
//        return roles;
//    }
//
//    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//
//        for (String role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role));
//        }
//        return authorities;
//    }
//}
