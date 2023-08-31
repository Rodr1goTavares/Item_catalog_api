CREATE TABLE IF NOT EXISTS tb_users(
    id SERIAL PRIMARY KEY,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(300) NOT NULL,
    user_role VARCHAR(100) NOT NULL,
    created_at DATE NOT NULL,
    updated_at DATE NOT NULL
);