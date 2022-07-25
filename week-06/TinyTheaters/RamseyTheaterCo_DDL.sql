-- Miro Stojanovic
-- Theaters Assessment
-- -------------------------------


-- Setup --
DROP DATABASE IF EXISTS ramsey_theater;
CREATE DATABASE ramsey_theater;
USE ramsey_theater;

DROP TABLE ticket;
DROP TABLE theater;
DROP TABLE customer;

-- Create Customer Table --
CREATE TABLE customer (
	customer_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone CHAR(14) NULL,
    address VARCHAR(250) NULL
);
   
-- Create Theater Table --
CREATE TABLE theater (
	theater_id INT PRIMARY KEY AUTO_INCREMENT,
    theater_name VARCHAR(100) NOT NULL,
    address VARCHAR(250) NOT NULL,
    phone CHAR(14) NOT NULL, 
    email VARCHAR(100) NOT NULL
);
    
-- Create Ticket Table --
CREATE TABLE ticket (
	ticket_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    theater_id INT NOT NULL,
    seat CHAR(2) NOT NULL,
    show_name VARCHAR(100) NOT NULL,
    ticket_price DECIMAL(4,2) NOT NULL,
    `date` DATE NOT NULL,
    CONSTRAINT fk_ticket_customer_id
		FOREIGN KEY (customer_id)
        REFERENCES customer(customer_id),
	CONSTRAINT fk_ticket_theater_id
		FOREIGN KEY (theater_id)
        REFERENCES theater(theater_id)
);
    