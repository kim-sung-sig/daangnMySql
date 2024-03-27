DROP TABLE MessageType;
CREATE TABLE MessageType (
    idx INT PRIMARY KEY AUTO_INCREMENT,
    typeStr VARCHAR(255) NOT NULL
);

INSERT INTO MessageType (typeStr) VALUES ('ENTER');
INSERT INTO MessageType (typeStr) VALUES ('TALK');
INSERT INTO MessageType (typeStr) VALUES ('LEAVE');
INSERT INTO MessageType (typeStr) VALUES ('RESERVE');

SELECT * FROM MessageType;