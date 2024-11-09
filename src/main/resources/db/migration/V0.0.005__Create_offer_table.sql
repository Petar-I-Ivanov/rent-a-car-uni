CREATE TABLE offer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    car_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    days_of_rent SMALLINT NOT NULL,
    total_price DECIMAL(15, 2) NOT NULL,
    accepted_at DATE,
    accepted BOOLEAN NOT NULL,
    expire_at DATE,
    expired BOOLEAN NOT NULL,
    active BOOLEAN NOT NULL,

    -- Foreign key constraints
    FOREIGN KEY (car_id) REFERENCES `car`(id),
    FOREIGN KEY (user_id) REFERENCES `user`(id)
);