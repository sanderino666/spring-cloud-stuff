DELETE FROM user;
INSERT INTO user VALUES (0, current_timestamp(), current_timestamp(), 'john.doe@example.com', 'John', 'Doe', 'user');
INSERT INTO user VALUES (1, current_timestamp(), current_timestamp(), 'john.doe2@example.com', 'John 2', 'Doe', 'aggregate');