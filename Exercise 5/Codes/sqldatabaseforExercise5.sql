-- Create the database
CREATE DATABASE user_login;

-- Use the database
USE user_login;

-- Create the accounts table
CREATE TABLE accounts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Insert test users into the accounts table
INSERT INTO accounts (username, password) VALUES ('testuser1', 'password123');
INSERT INTO accounts (username, password) VALUES ('testuser2', 'password456');

-- Show all tables in the database
SHOW TABLES;

-- Select all records from the accounts table to verify the data
SELECT * FROM accounts;

-- Optionally, add a UNIQUE constraint to the 'username' column to avoid duplicate usernames
ALTER TABLE accounts ADD CONSTRAINT unique_username UNIQUE (username);


