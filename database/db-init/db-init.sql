# Initialize database
CREATE DATABASE revenuerecognition;
USE revenuerecognition;

CREATE TABLE product (id int primary key, name varchar(50));
CREATE TABLE contract (id int primary key, product int, revenue decimal, dateSigned date);
CREATE TABLE revenuerecognition (id int primary key, contract int, amount decimal, recognizedOn date);
