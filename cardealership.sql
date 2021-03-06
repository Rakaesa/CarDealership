DROP DATABASE IF EXISTS carDealership;
CREATE DATABASE carDealership;
USE carDealership;

CREATE TABLE users(
    id INT primary key auto_increment,
    username varchar(50) NOT NULL UNIQUE,
    password varchar(255) not null,
    email varchar(50) not null,
    firstname varchar(50),
    lastname varchar(50)
);

CREATE TABLE make(
    id INT PRIMARY KEY AUTO_INCREMENT,
    make VARCHAR(30) NOT NULL,
    userid int,
    dateAdded date NOT NULL,
    foreign key (userid) references users(id)
);

CREATE TABLE model(
    id INT PRIMARY KEY AUTO_INCREMENT,
    model VARCHAR(30) NOT NULL,
    makeid int,
    userid int,
    dateAdded date NOT NULL,
    FOREIGN KEY (makeid) REFERENCES make(id),
    FOREIGN KEY (userid) REFERENCES users(id)
);

CREATE TABLE car(
    id INT PRIMARY KEY AUTO_INCREMENT,
    year int not null,
    type varchar(5) not null,
    mrsp double not null,
    price double not null,
    vin varchar(17) not null,
    interior varchar(10) not null,
    trans varchar(10) not null,
    color varchar(10) not null,
    bodystyle varchar(10) not null,
    description varchar(255) not null,
    featured bool not null,
    modelid int,
    purchased boolean,
    FOREIGN KEY (modelid) REFERENCES model(id)
);

CREATE TABLE specials(
    id INT primary key auto_increment,
    title varchar(50) NOT NULL,
    description varchar(1000) not null
);

CREATE TABLE contact(
    id INT primary key auto_increment,
    name varchar(50) NOT NULL,
    email varchar(50),
    phone varchar(15),
    message varchar(255)
);



CREATE TABLE roles(
    id INT primary key auto_increment,
    role varchar(50) NOT NULL
);

CREATE TABLE user_roles(
    userid INT not null,
    roleid int not null,
	FOREIGN KEY (userid) REFERENCES users(id),
    FOREIGN KEY (roleid) REFERENCES roles(id)
);

CREATE TABLE transactions(
    id INT primary key auto_increment,
    userid int,
    carid int,
    purchasedate date null,
    purchaseprice double NOT NULL,
    purchasetype varchar(20) NOT NULL,
    name varchar(50) NOT NULL,
    email varchar(50) NOT NULL,
    phone varchar(15),
    street1 varchar(50) NOT NULL,
    street2 varchar(50),
    city varchar(15) NOT NULL,
    state varchar(15) NOT NULL,
    zipcode varchar(10) NOT NULL,
	FOREIGN KEY (userid) REFERENCES users(id),
    FOREIGN KEY (carid) REFERENCES car(id)
);
CREATE TABLE salesreport(
    id INT primary key auto_increment,
    name varchar(50) not null,
    totalsales double not null,
    totalvehicles int not null
);

INSERT INTO roles VALUES("ADMIN");
INSERT INTO ROLES VALUES("SALES");
INSERT INTO users(username, password, email, firstname, lastname) VALUES("Admin", "$2a$10$1iyV.QqFFG/NSCrlYRzWfumIPjDRmU4K6MQy7B2dOdo/t6jFfbYwW", "admin@gmail.com", "Admin", "Admin");
INSERT INTO user_roles VALUES(1, 1);
INSERT INTO user_roles VALUES(1, 2);