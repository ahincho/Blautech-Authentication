-- V1 Create Users Table
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    firstname VARCHAR(64) NOT NULL,
    lastname VARCHAR(64) NOT NULL,
    email VARCHAR(128) UNIQUE NOT NULL,
    password VARCHAR(256) NOT NULL,
    address VARCHAR(256) NOT NULL,
    birthday DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);