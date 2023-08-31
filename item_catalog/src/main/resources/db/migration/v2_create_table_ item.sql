CREATE TABLE IF NOT EXISTS tb_item(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    userId INTEGER FOREIGN KEY,
    name VARCHAR(200) NOT NULL,
    unitValue DECIMAL(10, 2),
    amount INTEGER,
    totalValue DECIMAL(10, 2)
    createdAt TIME NOT NULL,
    updatedAt TIME NOT NULL
);