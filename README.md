##JSF 2 + Spring 4 + Spring Security 3 + Hibernate 4 integration

###First of all add dependencies to pom.xml:
[pom.xml](https://github.com/UnionOne/JSFSHSecurity/blob/master/pom.xml)
```xml
        ...
    <properties>
        <spring.version>4.0.3.RELEASE</spring.version>
        <spring.security.version>3.2.5.RELEASE</spring.security.version>
        <hibernate.version>4.3.5.Final</hibernate.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    </properties>
        ...
```

###Add listener, filter and context-param to web.xml:
[web.xml](https://github.com/UnionOne/JSFSHSecurity/blob/master/src/main/webapp/WEB-INF/web.xml)
```xml
      ...
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:spring/spring-security.xml classpath:spring/spring-hibernate.xml</param-value>
    </context-param>

    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    <listener>
        <listener-class>org.springframework.web.context.request.RequestContextListener</listener-class>
    </listener>

    <filter>
        <filter-name>springSecurityFilterChain</filter-name>
        <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>springSecurityFilterChain</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
      ...
```

###Then add el-resolver to faces-config.xml:
[faces-config.xml](https://github.com/UnionOne/JSFSHSecurity/blob/master/src/main/webapp/WEB-INF/faces-config.xml)
```xml
    <application>
        <el-resolver>
            org.springframework.web.jsf.el.SpringBeanFacesELResolver
        </el-resolver>
    </application>
```

###Configure saint spring-hibernate.xml in resources folder:
[spring-hibernate.xml](https://github.com/UnionOne/JSFSHSecurity/blob/master/src/main/resources/spring/spring-hibernate.xml)
```xml
      ...
    <beans:bean id="transactionManager" class="org.springframework.orm.hibernate4.HibernateTransactionManager">
        <beans:property name="sessionFactory" ref="hibernate4AnnotatedSessionFactory"/>
    </beans:bean>

    <beans:bean id="userDAO" class="com.itibo.dao.UserDAOImpl">
        <beans:property name="sessionFactory" ref="hibernate4AnnotatedSessionFactory"/>
    </beans:bean>

    <beans:bean id="userService" class="com.itibo.service.UserServiceImpl">
        <beans:property name="userDAO" ref="userDAO"/>
    </beans:bean>
      ...
```

###Configure saint spring-security.xml in resources folder:
[spring-security.xml](https://github.com/UnionOne/JSFSHSecurity/blob/master/src/main/resources/spring/spring-security.xml)
```xml
      ...
    <http auto-config="true">
        <intercept-url pattern="/pages/admin.xhtml" access="ROLE_ADMIN"/>
        <form-login login-page="/pages/login.xhtml"
                    default-target-url="/pages/admin.xhtml"
                    authentication-failure-url="/pages/login.xhtml?error"/>
        <logout logout-success-url="/pages/login.xhtml"/>
    </http>

    <authentication-manager>
        <authentication-provider user-service-ref="customUserDetailsService"/>
    </authentication-manager>
     ...
```

###Create new tables in some schema:
[setup.sql](https://github.com/UnionOne/JSFSHSecurity/blob/master/src/main/resources/hibernate/setup.sql)
```sql
  CREATE TABLE `users` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `roles` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `role` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `user_roles` (
  `user_id` int(6) NOT NULL,
  `role_id` int(6) NOT NULL,
  KEY `user` (`user_id`),
  KEY `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

###Create hibernate.cfg.xml to mapping all entities:
[hibernate.cfg.xml](https://github.com/UnionOne/JSFSHSecurity/blob/master/src/main/resources/hibernate/hibernate.cfg.xml)
```xml
        ...
<hibernate-configuration>
    <session-factory>
        <mapping class="com.itibo.model.User"/>
        <mapping class="com.itibo.model.Role"/>
    </session-factory>
</hibernate-configuration>
        ...
```

###Add needded model:
[model](https://github.com/UnionOne/JSFSHSecurity/tree/master/src/main/java/com/itibo/model)
```java
...
@Entity
@Table(name = "users")
@ManagedBean(name = "userModel")
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    private String login;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Role role;
...
```

###Add DAO:
[DAO](https://github.com/UnionOne/JSFSHSecurity/tree/master/src/main/java/com/itibo/dao)
```java
...
@Repository
@SuppressWarnings("unchecked")
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        user.setRole(new Role("user"));
        session.persist(user);
    }
...
```

###Add Service:
[Service](https://github.com/UnionOne/JSFSHSecurity/tree/master/src/main/java/com/itibo/service)
```java
...
@Repository
@SuppressWarnings("unchecked")
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        user.setRole(new Role("user"));
        session.persist(user);
    }
...
```

###Add custom <authentication-provider>:
[CustomUserDetailsService.java](https://github.com/UnionOne/JSFSHSecurity/tree/master/src/main/java/com/itibo/service)
```java
...
@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        com.itibo.model.User domainUser = userDAO.getUserByLogin(login);

        return new User(
                domainUser.getLogin(),
                domainUser.getPassword(),
                true, true, true, true,
                getAuthorities(domainUser.getRole().getId())
        );
    }
...
```
