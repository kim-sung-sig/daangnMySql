show tables;

drop table if exists daangn_member;
drop table if exists daangn_user_file;
drop table if exists daangn_status;
drop table if exists daangn_category;
drop table if exists daangn_comment;

drop table if exists notices;
drop table if exists tb_visitor;

-- 맴버
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
VALUES ('testuser6', '123456', 'ROLE_USER', '테스트유저6', '중고보물찾기', ' ', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ' ', ' ');
INSERT INTO daangn_member (username, password, role, name, nickName, email, emailOk, signUpDate, lastLoginDate, stAddress, dtAddress) 
VALUES ('testuser7', '123456', 'ROLE_USER', '테스트유저7', '믿음직한구매자', ' ', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ' ', ' ');
INSERT INTO daangn_member (username, password, role, name, nickName, email, emailOk, signUpDate, lastLoginDate, stAddress, dtAddress) 
VALUES ('testuser8', '123456', 'ROLE_USER', '테스트유저8', '따뜻한이웃', ' ', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ' ', ' ');
INSERT INTO daangn_member (username, password, role, name, nickName, email, emailOk, signUpDate, lastLoginDate, stAddress, dtAddress) 
VALUES ('testuser9', '123456', 'ROLE_USER', '테스트유저9', '당근종합프로', ' ', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ' ', ' ');

-- 유저파일
CREATE TABLE daangn_user_file(
    idx INT PRIMARY KEY AUTO_INCREMENT,
    ref INT NOT NULL,
    originFileName VARCHAR(1000) NOT NULL,
    saveFileName VARCHAR(1000) NOT NULL,
    CONSTRAINT fk_daangn_user_file_ref FOREIGN KEY (ref) REFERENCES daangn_member(idx) ON DELETE CASCADE
);

-- 유저평
CREATE TABLE daangn_comment(
	idx INT PRIMARY KEY AUTO_INCREMENT,
	userRef INT NOT NULL,
	writerRef INT NOT NULL,
	score INT NOT NULL,
	content VARCHAR(200) NOT NULL,
	createDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	ip VARCHAR(200) NOT NULL,
	CONSTRAINT fk_daangn_comment_userIdx_ref FOREIGN KEY (userRef) REFERENCES daangn_member(idx) ON DELETE CASCADE,
	CONSTRAINT fk_daangn_comment_wrIdx_ref FOREIGN KEY (writerRef) REFERENCES daangn_member(idx) ON DELETE CASCADE
);

-- 공지사항
CREATE TABLE notices(
	idx INT PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(500) NOT NULL,
	content TEXT NOT NULL,
	regDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	highlight INT DEFAULT 0
);


-- 방문객
CREATE TABLE tb_visitor (
	idx INT PRIMARY KEY AUTO_INCREMENT,							-- 키필드
	visitIp VARCHAR(50) NOT NULL,									-- 접속자 ip
	visitTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL		-- 방문 시간
);


-- 인기
CREATE TABLE tb_popular(
	idx INT PRIMARY KEY AUTO_INCREMENT,
	boardRef INT NOT NULL,
	userRef INT NOT NULL,
	interaction INT NOT NULL,
	interaction_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT fk_tb_popular_board_ref FOREIGN KEY (boardRef) REFERENCES daangn_board(idx) ON DELETE CASCADE,
	CONSTRAINT fk_tb_popular_member_ref FOREIGN KEY (userRef) REFERENCES daangn_member(idx) ON DELETE CASCADE	
);