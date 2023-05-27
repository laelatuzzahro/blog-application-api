DROP TABLE IF EXISTS post;
CREATE TABLE post (
    id numeric AUTO_INCREMENT PRIMARY KEY,
    author VARCHAR(255),
    title VARCHAR(255),
    body TEXT
);