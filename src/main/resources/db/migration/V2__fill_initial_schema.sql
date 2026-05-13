INSERT INTO `gender`(`name`) VALUES ('Male'), ('Female');

INSERT INTO `job_titles`(`name`) VALUES ('Software Engineer'), ('Data Scientist'), ('Product Manager'), ('Designer'), ('QA Engineer');

INSERT INTO `user`(`birth_date`, `breakfast_time`, `created_at`, `first_surname`, `name`, `password`, `second_surname`, `username`, `gender_id`, `job_title_id`) VALUES
('1990-01-01', '08:00:00', '2024-06-01 12:00:00', 'Smith', 'John', 'password123', 'Doe', 'johnsmith', 1, 1),
('1985-05-15', '07:30:00', '2024-06-01 12:05:00', 'Johnson', 'Emily', 'password456', NULL, 'emilyjohnson', 2, 2),
('1992-09-10', '08:15:00', '2024-06-01 12:10:00', 'Brown', 'Michael', 'password789', NULL, 'michaelbrown', 1, 3);

INSERT INTO `address`(`main_address`, `street_name`, `street_number`, `user_id`) VALUES
(1, 'Main Street', 123, 1),
(0, 'Second Street', 456, 1),
(1, 'Third Street', 789, 2);