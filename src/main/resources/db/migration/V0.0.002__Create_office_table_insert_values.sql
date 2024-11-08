-- Create the Office table
CREATE TABLE office (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    country VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    address VARCHAR(255) NOT NULL
);

-- Insert sample records
INSERT INTO office VALUES 
(NEXT VALUE FOR hibernate_sequence, 'Bulgaria', 'Sofia', '123 Main St, Sofia'),
(NEXT VALUE FOR hibernate_sequence, 'Bulgaria', 'Plovdiv', '456 Central Ave, Plovdiv'),
(NEXT VALUE FOR hibernate_sequence, 'Bulgaria', 'Burgas', '789 Seaside Blvd, Burgas'),
(NEXT VALUE FOR hibernate_sequence, 'Bulgaria', 'Varna', '101 Riverside Dr, Varna');