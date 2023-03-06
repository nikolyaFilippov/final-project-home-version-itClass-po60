CREATE DATABASE pizza_web;

use pizza_web;

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


CREATE TABLE foodItem (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    foodTypeId int NOT NULL,
    name varchar(50) NOT NULL,
    price double NOT NULL
);

INSERT INTO foodItem (foodTypeId, name, price) VALUES
    (1, 'Margherita', 15.99),
    (1, 'Napoletana', 17.99),
    (1, 'Carbonara', 25.99),
    (1, 'Peperoni', 25.99),
    (2, 'Beer', 9.50),
    (2, 'Cola', 2.50),
    (2, 'Tea', 3.50),
    (2, 'Coffee', 9.50);

CREATE TABLE orders (
    id varchar(50) NOT NULL PRIMARY KEY,
    date date NOT NULL,
    userId int NOT NULL,
    address varchar(50) NOT NULL,
    FOREIGN KEY (userId) REFERENCES user (id)
                    ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE orderItem (
    orderId varchar(50) NOT NULL,
    itemId int NOT NULL,
    quantity int NOT NULL,
    FOREIGN KEY (orderId) REFERENCES orders (id)
                    ON DELETE CASCADE ON UPDATE RESTRICT,
    FOREIGN KEY (itemId) REFERENCES foodItem (id)
                    ON DELETE CASCADE ON UPDATE RESTRICT
);