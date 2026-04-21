DROP
DATABASE IF EXISTS wishes;
CREATE
DATABASE IF NOT EXISTS wishes;
USE
wishes;

SET
FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS wish_tag;
DROP TABLE IF EXISTS tag;
DROP TABLE IF EXISTS wish;
DROP TABLE IF EXISTS wish_list;
DROP TABLE IF EXISTS user;
SET
FOREIGN_KEY_CHECKS = 1;

CREATE TABLE user
(
    user_id  INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(15) NOT NULL,
    password VARCHAR(25) NOT NULL
);

CREATE TABLE wish_list
(
    wish_list_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id      INT          NOT NULL,
    name         VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE
);

CREATE TABLE wish
(
    wish_id      INT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    description  VARCHAR(255),
    price DOUBLE,
    quantity     INT,
    product_link VARCHAR(2048),
    wish_list_id INT          NOT NULL,
    FOREIGN KEY (wish_list_id) REFERENCES wish_list (wish_list_id) ON DELETE CASCADE
);

CREATE TABLE tag
(
    tag_id   INT AUTO_INCREMENT PRIMARY KEY,
    tag_name VARCHAR(255)
);

CREATE TABLE wish_tag
(
    wish_id INT,
    tag_id  INT,
    PRIMARY KEY (wish_id, tag_id),
    FOREIGN KEY (wish_id) REFERENCES wish (wish_id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES tag (tag_id) ON DELETE CASCADE
);