CREATE DATABASE IF NOT EXISTS pizza_web;

use pizza_web;

DROP TABLE IF EXISTS user;

CREATE TABLE user (
    id int AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(32) NOT NULL,
    name VARCHAR(32) NOT NULL, 
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

INSERT INTO user (login, name, password, email) VALUES
	('vano', 'Ivan', '1234', 'ivan@mail.ru'),
	('pit', 'Petr', '4321', 'petr@mail.ru');	




CREATE TABLE IF NOT EXISTS customer (
	id int NOT NULL AUTO_INCREMENT,
	firstName varchar(50) NOT NULL,
	lastName varchar(50) NOT NULL,
	email varchar(50) NOT NULL,
	PRIMARY KEY (customerId)
);


CREATE TABLE IF NOT EXISTS deliveryAddress (
	deliveryAddressId int NOT NULL AUTO_INCREMENT,
	streetName varchar(50) NOT NULL,
	houseNumber varchar(50) NOT NULL,
	flatNumber varchar(50) NOT NULL,
	PRIMARY KEY (deliveryAddressId)
);
INSERT INTO deliveryAddress(streetName, houseNumber, flatNumber) VALUES 
	('Sovetskaya', '25a', '15'),
	('Lenina', '10', '0');

CREATE TABLE IF NOT EXISTS foodType (
	foodTypeId int NOT NULL AUTO_INCREMENT,
	name varchar(50) NOT NULL,
	PRIMARY KEY (foodTypeId)
);
INSERT INTO foodType (name) VALUES 
	('PIZZA'),
	('DRINK');
	
CREATE TABLE IF NOT EXISTS foodItem (
	foodItemId int NOT NULL AUTO_INCREMENT,
	foodTypeId int NOT NULL,
	name varchar(50) NOT NULL,
	price double NOT NULL,
	PRIMARY KEY (foodItemId),
	FOREIGN KEY (foodTypeId) REFERENCES foodType (foodTypeId)
				ON DELETE CASCADE ON UPDATE RESTRICT
);
INSERT INTO foodItem (foodTypeId, name, price) VALUES
	(1, 'Margherita', 8.5),
	(1, 'Napoletana', 8.5),
	(2, 'Tea', 2.5),
	(2, 'Coffee', 5.5);


INSERT INTO customer (firstName, lastName, email) VALUES
	('Ivan', 'Ivanov', 'ivan@mail.ru'),
	('Petr', 'Petrov', 'petr@mail.ru');	



CREATE TABLE IF NOT EXISTS orders (
	orderId int NOT NULL AUTO_INCREMENT,
	date date NOT NULL,
	customer int NOT NULL,
	address int NOT NULL,   
	PRIMARY KEY (orderId),
	FOREIGN KEY (customer) REFERENCES customer (customerId)
                    ON DELETE CASCADE ON UPDATE RESTRICT,
	FOREIGN KEY (address) REFERENCES deliveryAddress (deliveryAddressId)
                    ON DELETE CASCADE ON UPDATE RESTRICT
);
	
CREATE TABLE IF NOT EXISTS orderItem (
	orderId int NOT NULL,
	item int NOT NULL,
	quantity int NOT NULL,
    FOREIGN KEY (orderId) REFERENCES orders (orderId)
                    ON DELETE CASCADE ON UPDATE RESTRICT,
	FOREIGN KEY (item) REFERENCES foodItem (foodItemId)
                    ON DELETE CASCADE ON UPDATE RESTRICT				
);
_________________________________________________________________________


INSERT INTO orders (orderId, date, customer, address) VALUES 
	(15, '2008-11-11', 1, 1),
	(2, '2008-11-11', 1, 1),
	(8, '2008-11-11', 1, 1),
	(3, '2008-11-11', 1, 1)


SELECT MAX(orderId) FROM orders 

SELECT customerId FROM customer WHERE  firstName = 'Ivan' AND lastName = 'Ivanov' AND email = 'petr@mail.ru'

SELECT deliveryAddressId
FROM deliveryAddress
WHERE streetName = 'Sovetskaya'
AND houseNumber = '25a'
AND flatNumber = '15'

INSERT INTO orderItem (item, quantity) VALUES (1, 2)