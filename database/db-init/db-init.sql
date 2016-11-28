# Initialize database
CREATE DATABASE revenuerecognition;
USE revenuerecognition;

CREATE TABLE product (id int primary key, name varchar(50), strategy varchar(50), offset1 int, offset2 int);
CREATE TABLE contract (id int primary key, product int, revenue decimal, dateSigned date);
CREATE TABLE revenuerecognition (id int primary key, contract int, amount double, recognizedOn date);
