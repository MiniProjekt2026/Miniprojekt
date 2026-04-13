DROP
DATABASE if exists wishes;
create
database if not exists wishes;
use
wishes;
select database();

SET
FOREIGN_KEY_CHECKS = 0;
drop table if exists wish_list_tag;
drop table if exists tag;
drop table if exists wish_list;
drop table if exists user;
SET
FOREIGN_KEY_CHECKS = 1;

create table wish_list
(
    wish_id      int AUTO_INCREMENT primary key,
    name         VARCHAR(255) not null,
    description  VARCHAR(255) not null,
    price double not null,
    quantity     int,
    product_link VARCHAR(255) not null,
    foreign key (user_id) REFERENCES user (user_id) ON DELETE CASCADE
);

create table tag
(
    tag_id   int AUTO_INCREMENT primary key,
    tag_name VARCHAR(255)
);

create table wish_list_tag
(
    wish_id int AUTO_INCREMENT primary key,
    tag_id  int AUTO_INCREMENT primary key,
    foreign key (wish_id) REFERENCES wish_list (wish_id) ON DELETE CASCADE,
    foreign key (tag_id) REFERENCES tag (tag_id) ON DELETE CASCADE
);

create table user
(
    user_id  int AUTO_INCREMENT primary key,
    username VARCHAR(15) NOT NULL,
    password VARCHAR(25) NOT NULL
);


