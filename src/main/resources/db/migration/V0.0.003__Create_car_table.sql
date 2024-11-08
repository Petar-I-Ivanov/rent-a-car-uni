CREATE TABLE car (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand VARCHAR(50) NOT NULL,
    model VARCHAR(255) NOT NULL,
    office_id BIGINT,
    price_per_day DECIMAL(10, 2) NOT NULL,
    taken BOOLEAN NOT NULL,
    active BOOLEAN NOT NULL,
    FOREIGN KEY (office_id) REFERENCES office(id)
);