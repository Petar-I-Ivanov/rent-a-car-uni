CREATE TABLE `users` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(60) NOT NULL,
    last_name VARCHAR(60) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone VARCHAR(30) NOT NULL UNIQUE,
    age SMALLINT NOT NULL,
    has_car_accidents BOOLEAN NOT NULL,
    active BOOLEAN NOT NULL
);

INSERT INTO `users` VALUES 
(NEXT VALUE FOR hibernate_sequence, 'Petar', 'Ivanov', 'Sliven', '0888888888', 23, false, true),
(NEXT VALUE FOR hibernate_sequence, 'Georgi', 'Georgiev', 'Plovdiv', '0877777777', 20, true, true),
(NEXT VALUE FOR hibernate_sequence, 'Ivan', 'Ivanov', 'Sofia', '0866666666', 30, false, true);