CREATE TABLE users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(15) NOT NULL,
                       password VARCHAR(25) NOT NULL
);

CREATE TABLE wish_list (
                           wish_list_id INT AUTO_INCREMENT PRIMARY KEY,
                           user_id INT NOT NULL,
                           name VARCHAR(255) NOT NULL,
                           FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE wish (
                      wish_id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      description VARCHAR(255),
                      price DOUBLE,
                      quantity INT,
                      product_link VARCHAR(255),
                      wish_list_id INT NOT NULL,
                      FOREIGN KEY (wish_list_id) REFERENCES wish_list(wish_list_id)
);

CREATE TABLE tag (
                     tag_id INT AUTO_INCREMENT PRIMARY KEY,
                     tag_name VARCHAR(255)
);

CREATE TABLE wish_tag (
                          wish_id INT,
                          tag_id INT,
                          PRIMARY KEY (wish_id, tag_id),
                          FOREIGN KEY (wish_id) REFERENCES wish(wish_id),
                          FOREIGN KEY (tag_id) REFERENCES tag(tag_id)
);