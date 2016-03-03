##JSF 2 + Spring 3 + Spring Secutity + Hibernate integration
(not working normal yet)

###First of all add dependencies to pom.xml:
[pom.xml](https://github.com/UnionOne/JSFSHSecurity/blob/master/pom.xml#L16-L19)
```xml
        ...
    <properties>
        <jsf.version>2.2.10</jsf.version>
        <spring.version>3.2.8.RELEASE</spring.version>
        <spring.security.version>3.2.5.RELEASE</spring.security.version>
        <hibernate.version>4.1.8.Final</hibernate.version>
        <mysql.driver.version>5.1.3</mysql.driver.version>
    </properties>
        ...
```

###Then add some lines to faces-config.xml:
[faces-config.xml](https://github.com/UnionOne/JSFSHSecurity/blob/master/src/main/webapp/WEB-INF/faces-config.xml#L7-L11)
```xml
    <application>
        <el-resolver>
            org.springframework.web.jsf.el.SpringBeanFacesELResolver
        </el-resolver>
    </application>
```

###Configure saint spring-hibernate.xml in WEB-INF folder:
[spring-hibernate.xml](https://github.com/UnionOne/JSFSHSecurity/blob/master/src/main/webapp/WEB-INF/spring-hibernate.xml#L13-L30)
```xml
      ...
    <bean id="sessionFactory" class="org.springframework.orm.hibernate4.LocalSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="configLocation" value="classpath:hibernate.cfg.xml"/>
        <property name="hibernateProperties">
            <props>
                <prop key="hibernate.dialect">org.hibernate.dialect.DB2Dialect</prop>
                <prop key="hibernate.format_sql">true</prop>
            </props>
        </property>
    </bean>

    <bean id="transactionManager" class="org.springframework.orm.hibernate4.HibernateTransactionManager">
        <property name="sessionFactory" ref="sessionFactory"/>
    </bean>
      ...
```

###Configure saint spring-security.xml in WEB-INF folder:
[spring-security.xml](https://github.com/UnionOne/JSFSHSecurity/blob/master/src/main/webapp/WEB-INF/spring-security.xml#L10-L16)
```xml
      ...
    <http auto-config="true">
        <form-login login-page="/login.jsf" default-target-url="/welcome.jsf"
                    authentication-failure-url="/login.jsf?status=error"/>
        <intercept-url pattern="/welcome.jsf" access="ROLE_USER"/>
        <intercept-url pattern="/admin*" access="ROLE_ADMIN"/>
        <logout logout-success-url="/login.jsf?status=logout"/>
    </http>
     ...
```

###Add listener and context-param to web.xml:
[web.xml](https://github.com/UnionOne/JSFSHSecurity/blob/master/src/main/webapp/WEB-INF/web.xml#L56-L68)
```xml
      ...
    <!-- Add Support for Spring -->
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    <listener>
        <listener-class>org.springframework.web.context.request.RequestContextListener</listener-class>
    </listener>

    <!-- context-param -->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/spring-hibernate.xml, /WEB-INF/spring-security.xml</param-value>
    </context-param>
      ...
```

###Create new tables in some schema:
[setup.sql](https://github.com/UnionOne/JSFSHSecurity/blob/master/src/main/resources/setup.sql#L1-L16)
```sql
  CREATE TABLE USER (
  USER_ID int(10) NOT NULL AUTO_INCREMENT,
  LOGIN varchar(50) NOT NULL,
  PWD varchar(100) NOT NULL,
  ENABLED tinyint(1) DEFAULT NULL,
  PRIMARY KEY (USER_ID)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
 
CREATE TABLE ROLE (
  ROLE_ID int(10) NOT NULL AUTO_INCREMENT,
  USER_ID int(10) DEFAULT NULL,
  CODE varchar(60) NOT NULL,
  LABEL varchar(100) NOT NULL,
  PRIMARY KEY (ROLE_ID),
  CONSTRAINT FK_USROLE FOREIGN KEY (USER_ID) REFERENCES USER (USER_ID)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
```

###Create hibernate config to all entities:
[User.hbm.xml](https://github.com/UnionOne/JSFSHSecurity/blob/master/src/main/resources/com/itibo/spring/persistence/User.hbm.xml#L5-L27)
```xml
        ...
        <property name="login" type="string">
            <column name="LOGIN" length="50" not-null="true"/>
        </property>
        <property name="pwd" type="string">
            <column name="PWD" length="100" not-null="true"/>
        </property>
        <property name="enabled" type="java.lang.Integer">
            <column name="ENABLED"/>
        </property>
        ...
```

###Create hibernate config to all entities:
[Role.hbm.xml](https://github.com/UnionOne/JSFSHSecurity/blob/master/src/main/resources/com/itibo/spring/persistence/Role.hbm.xml#L4-L20)
```xml
        ...
        <property name="code" type="string">
            <column name="CODE" length="60" not-null="true" unique="true"/>
        </property>
        <property name="label" type="string">
            <column name="LABEL" length="100" not-null="true"/>
        </property>
        ...
```

###Add hibernate mapping to all configs:
[hibernate.cfg.xml](https://github.com/UnionOne/JSFSHSecurity/blob/master/src/main/resources/hibernate.cfg.xml#L5-L10)
```xml
                  ...
        <hibernate-configuration>
            <session-factory>
                <mapping resource="com/itibo/spring/persistence/User.hbm.xml"/>
                <mapping resource="com/itibo/spring/persistence/Role.hbm.xml"/>
            </session-factory>
        </hibernate-configuration>
                  ...
```

###Add persistance classes:
[persistence](https://github.com/UnionOne/JSFSHSecurity/blob/master/src/main/java/com/itibo/spring/persistence/Role.java#L7-L11)
```java
...
public class Role {
    private int roleId;
    private String code;
    private String label;
    private User user;
...
```

###Add model classes:
[model](https://github.com/UnionOne/JSFSHSecurity/blob/master/src/main/java/com/itibo/spring/model/UserModel.java#L7-L10)
```java
...
public class UserModel {
    private String login;
    private String pwd;
    private String pwdConfirm;
...
```

###Add DAO Controller and Manger classes:
[DAO](https://github.com/UnionOne/JSFSHSecurity/blob/master/src/main/java/com/itibo/spring/dao/UserDAO.java#L18-L27)
```java
...
@SuppressWarnings("ALL")
@Named
@Transactional("transactionManager")
public class UserDAO {
    @Inject
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
...
```
