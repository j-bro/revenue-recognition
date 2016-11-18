# Initialize database
CREATE DATABASE revenuerecognition;
USE revenuerecognition;

CREATE TABLE product (id int primary key, name varchar(50));
CREATE TABLE contract (id int primary key, product int, revenue decimal, dateSigned date);
CREATE TABLE revenuerecognition (contract int, amount decimal, recognizedOn date, PRIMARY KEY (contract, recognizedOn));


# Insert test data
INSERT INTO product values (1, 'Product 1');
INSERT INTO product values (2, 'Product 2');

INSERT INTO contract values (1, 1, 10, '2007-12-03T10:15:30+01:00[Europe/Paris]');
INSERT INTO contract values (2, 2, 20, '20016-12-03T10:40:30+01:00[Europe/London]');
