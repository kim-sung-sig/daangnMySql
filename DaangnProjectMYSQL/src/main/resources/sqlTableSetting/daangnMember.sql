DROP TABLE daangn_member;
CREATE TABLE daangn_member (
    idx INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(10) NOT NULL,
    name VARCHAR(100) NOT NULL,
    nickName VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    emailOk CHAR(1) DEFAULT '0', -- 1이 수신 거부
    signUpDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    lastLoginDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    stAddress VARCHAR(100) NOT NULL,
    dtAddress VARCHAR(100) NOT NULL
);

SELECT * FROM daangn_member;

INSERT INTO daangn_member (username, password, role, name, nickName, email, emailOk, signUpDate, lastLoginDate, stAddress, dtAddress) 
VALUES ('admin', '123456', 'ROLE_ADMIN', 'admin', 'admin', ' ', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ' ', ' ');
INSERT INTO daangn_member (username, password, role, name, nickName, email, emailOk, signUpDate, lastLoginDate, stAddress, dtAddress) 
VALUES ('master', '123456', 'ROLE_ADMIN', 'master', 'master', ' ', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ' ', ' ');
INSERT INTO daangn_member (username, password, role, name, nickName, email, emailOk, signUpDate, lastLoginDate, stAddress, dtAddress) 
VALUES ('webmaster', '123456', 'ROLE_ADMIN', 'webmaster', 'webmaster', ' ', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ' ', ' ');
INSERT INTO daangn_member (username, password, role, name, nickName, email, emailOk, signUpDate, lastLoginDate, stAddress, dtAddress) 
VALUES ('root', '123456', 'ROLE_ADMIN', 'root', 'root', ' ', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ' ', ' ');
INSERT INTO daangn_member (username, password, role, name, nickName, email, emailOk, signUpDate, lastLoginDate, stAddress, dtAddress) 
VALUES ('dba', '123456', 'ROLE_ADMIN', 'dba', 'dba', ' ', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ' ', ' ');