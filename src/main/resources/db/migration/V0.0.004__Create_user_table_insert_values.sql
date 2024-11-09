CREATE TABLE `user` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(60) NOT NULL,
    lastName VARCHAR(60) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone VARCHAR(30) NOT NULL UNIQUE,
    age SMALLINT NOT NULL,
    hasCarAccidents BOOLEAN NOT NULL,
    active BOOLEAN NOT NULL
);

INSERT INTO `user` VALUES 
(NEXT VALUE FOR hibernate_sequence, 'Petar', 'Ivanov', 'Sliven', '0888888888', 23, false, true),
(NEXT VALUE FOR hibernate_sequence, 'Georgi', 'Georgiev', 'Plovdiv', '0877777777', 20, true, true),
(NEXT VALUE FOR hibernate_sequence, 'Ivan', 'Ivanov', 'Sofia', '0866666666', 30, false, true);