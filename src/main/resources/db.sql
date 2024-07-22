CREATE DATABASE `wsda_card` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

CREATE TABLE wsda_card.cards (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activated` bit(1) NOT NULL,
  `credit` float NOT NULL,
  `number` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5x18bvmt7ovkbhkq1n7bh4uko` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE wsda_card.roles (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ofx66keruapi6vyqpv6f2or37` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE wsda_card.users (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activated` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE wsda_card.users_roles (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
  KEY `FK2o0jvgh89lemvvo17cbqvdxaa` (`user_id`),
  CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE wsda_card.transactions (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` float NOT NULL,
  `timestamp` datetime(6) NOT NULL,
  `card_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjxdscq0bxpy0pl465vvsqc89j` (`card_id`),
  KEY `FKqwv7rmvc8va8rep7piikrojds` (`user_id`),
  CONSTRAINT `FKjxdscq0bxpy0pl465vvsqc89j` FOREIGN KEY (`card_id`) REFERENCES `cards` (`id`),
  CONSTRAINT `FKqwv7rmvc8va8rep7piikrojds` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO wsda_card.roles (name) VALUES
	 ('ROLE_ADMIN'),
	 ('ROLE_MERCHANT');

INSERT INTO wsda_card.users (activated,password,username) VALUES
	 (1,'$2a$12$X4gzqeD0V48fwZgWuSl3QOL7GI4imaqiNLpp1ZcaEUMSyZJqzG4Ka','admin'),
	 (1,'$2a$10$Lv3h9L1.flfAi2xAjEz4muIz/CDgBIzXFKkeBUOEZvdfu3NfAW0FG','merchant'),
	 (0,'$2a$10$pIZGYZcqTzB0l1BjB7Rq2.N//u4e/QGQLdojXCG7iAvbm0c6OQQ3O','merchant2');


INSERT INTO wsda_card.cards (activated,credit,`number`) VALUES
	 (1,50.0,100),
	 (1,100.0,101),
	 (0,200.0,102);

INSERT INTO wsda_card.users_roles (user_id,role_id) VALUES
	 (1,1),
	 (2,2),
	 (3,2);

INSERT INTO wsda_card.transactions (amount,`timestamp`,card_id,user_id) VALUES
	 (-29.99,'2023-07-11 18:10:05.000',1,1),
	 (50.0,'2023-07-11 18:15:06.000',2,1),
	 (-19.9,'2023-07-11 18:15:18.000',3,1),
	 (25.0,'2023-07-11 18:15:55.000',1,2),
	 (-50.0,'2023-07-11 18:16:08.000',2,2),
	 (10.0,'2023-07-11 18:16:13.000',3,2),
	 (-9.99,'2023-07-11 18:16:52.000',2,3),
	 (100.0,'2023-07-11 18:16:57.000',3,3),
	 (80.0,'2023-07-11 18:17:21.000',3,3);


