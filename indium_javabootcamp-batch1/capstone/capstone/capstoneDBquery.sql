
create database skilltracker;
show databases;

use skilltracker;
show tables;

CREATE TABLE associates (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT,
    business_unit VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

select * from associates;
DELETE FROM associates;

DELETE FROM associates;
truncate table associates;
CREATE TABLE skills (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    category VARCHAR(255) NOT NULL,
    experience INT,
    associate_id INT,
    FOREIGN KEY (associate_id) REFERENCES associates(id)
);
select * from skills;