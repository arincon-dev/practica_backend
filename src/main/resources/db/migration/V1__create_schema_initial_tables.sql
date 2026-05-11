
CREATE TABLE `fake_entity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
;

CREATE TABLE `gender` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKjdyxf8c9k9656frmqjb67cgo3` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
;

CREATE TABLE `job_titles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `birth_date` datetime(6) NOT NULL,
  `breakfast_time` time(6) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `first_surname` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `second_surname` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `gender_id` int NOT NULL,
  `job_title_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcbf93j56y7t2tyhunb4neewva` (`gender_id`),
  KEY `FK4t20fi2d0fe3twrmf2tc4e9fo` (`job_title_id`),
  CONSTRAINT `FK4t20fi2d0fe3twrmf2tc4e9fo` FOREIGN KEY (`job_title_id`) REFERENCES `job_titles` (`id`),
  CONSTRAINT `FKcbf93j56y7t2tyhunb4neewva` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
;

CREATE TABLE `address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `main_address` bit(1) NOT NULL,
  `street_name` varchar(255) NOT NULL,
  `street_number` int DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKda8tuywtf0gb6sedwk7la1pgi` (`user_id`),
  CONSTRAINT `FKda8tuywtf0gb6sedwk7la1pgi` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
;