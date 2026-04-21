INSERT INTO users (username, password) VALUES ('testuser', '1234');

INSERT INTO wish_list (user_id, name) VALUES (1, 'Birthday');

INSERT INTO wish (name, description, price, quantity, product_link, wish_list_id)
VALUES ('Laptop', 'Gaming laptop', 1500, 1, 'http://test.com', 1);

INSERT INTO tag (tag_name) VALUES ('Electronics');

INSERT INTO wish_tag (wish_id, tag_id) VALUES (1, 1);