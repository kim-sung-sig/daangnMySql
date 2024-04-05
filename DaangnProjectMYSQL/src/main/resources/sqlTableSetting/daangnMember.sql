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

INSERT INTO daangn_member (username, password, role, name, nickName, email, emailOk, signUpDate, lastLoginDate, stAddress, dtAddress) 
VALUES ('testuser1', '123456', 'ROLE_USER', '테스트유저1', '재미있는중고', ' ', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ' ', ' ');
INSERT INTO daangn_member (username, password, role, name, nickName, email, emailOk, signUpDate, lastLoginDate, stAddress, dtAddress) 
VALUES ('testuser2', '123456', 'ROLE_USER', '테스트유저2', '중고보물찾기', ' ', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ' ', ' ');
INSERT INTO daangn_member (username, password, role, name, nickName, email, emailOk, signUpDate, lastLoginDate, stAddress, dtAddress) 
VALUES ('testuser3', '123456', 'ROLE_USER', '테스트유저3', 'Carrot매니아', ' ', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ' ', ' ');
INSERT INTO daangn_member (username, password, role, name, nickName, email, emailOk, signUpDate, lastLoginDate, stAddress, dtAddress) 
VALUES ('testuser4', '123456', 'ROLE_USER', '테스트유저4', 'devel당근', ' ', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ' ', ' ');
INSERT INTO daangn_member (username, password, role, name, nickName, email, emailOk, signUpDate, lastLoginDate, stAddress, dtAddress) 
VALUES ('testuser5', '123456', 'ROLE_USER', '테스트유저5', '당근Hunter', ' ', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ' ', ' ');
INSERT INTO daangn_member (username, password, role, name, nickName, email, emailOk, signUpDate, lastLoginDate, stAddress, dtAddress) 
VALUES ('testuser6', '123456', 'ROLE_USER', '테스트유저6', '중고보물', ' ', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ' ', ' ');
INSERT INTO daangn_member (username, password, role, name, nickName, email, emailOk, signUpDate, lastLoginDate, stAddress, dtAddress) 
VALUES ('testuser7', '123456', 'ROLE_USER', '테스트유저7', '믿음직한구매자', ' ', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ' ', ' ');
INSERT INTO daangn_member (username, password, role, name, nickName, email, emailOk, signUpDate, lastLoginDate, stAddress, dtAddress) 
VALUES ('testuser8', '123456', 'ROLE_USER', '테스트유저8', '따뜻한이웃', ' ', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ' ', ' ');
INSERT INTO daangn_member (username, password, role, name, nickName, email, emailOk, signUpDate, lastLoginDate, stAddress, dtAddress) 
VALUES ('testuser9', '123456', 'ROLE_USER', '테스트유저9', '당근종합프로', ' ', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ' ', ' ');