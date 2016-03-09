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

INSERT INTO roles (role) VALUES ('admin'), ('user');

INSERT INTO users (login, password) VALUES ('user', 'user'), ('admin', 'admin');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 2), (2, 1);