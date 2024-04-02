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

-- 상품상태
CREATE TABLE  daangn_status(
	statusIdx INT PRIMARY KEY AUTO_INCREMENT,
	statusName VARCHAR(100) NOT NULL
);

INSERT INTO daangn_status (statusName) VALUES ('판매중'); -- 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_status (statusName) VALUES ('예약중'); -- 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_status (statusName) VALUES ('판매완료');



-- 중고 카테고리
CREATE TABLE  daangn_category(
	categoryIdx INT PRIMARY KEY AUTO_INCREMENT,
	categoryName VARCHAR(100) NOT NULL
);

INSERT INTO daangn_category (categoryName) VALUES ('디지털/가전');			-- 1 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_category (categoryName) VALUES ('가구/인테리어');		-- 2 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_category (categoryName) VALUES ('유아동/유아도서');		-- 3 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_category (categoryName) VALUES ('생활/가공식품');		-- 4 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_category (categoryName) VALUES ('스포츠/레저');			-- 5 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_category (categoryName) VALUES ('여성잡화');			-- 6 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_category (categoryName) VALUES ('여성의류');			-- 7 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_category (categoryName) VALUES ('남성패션/잡화');		-- 8 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_category (categoryName) VALUES ('게임/취미');			-- 9 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_category (categoryName) VALUES ('뷰티/미용');			-- 10 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_category (categoryName) VALUES ('반려동물용품');		-- 11 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_category (categoryName) VALUES ('도서/티켓/음반');		-- 12 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_category (categoryName) VALUES ('기타'); 				-- 13 여기서 이름을 일단 미리 만들어 주자


-- 유저평
CREATE TABLE daangn_comment(
	idx INT PRIMARY KEY AUTO_INCREMENT,
	userIdx INT NOT NULL,
	writerIdx INT NOT NULL,
	score INT NOT NULL,
	content VARCHAR(200) NOT NULL,
	regDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT fk_daangn_comment_userIdx_ref FOREIGN KEY (userIdx) REFERENCES daangn_member(idx) ON DELETE CASCADE,
	CONSTRAINT fk_daangn_comment_wrIdx_ref FOREIGN KEY (writerIdx) REFERENCES daangn_member(idx) ON DELETE CASCADE
);


-- 중고마켓
CREATE TABLE daangn_board(
	idx INT PRIMARY KEY AUTO_INCREMENT,
	userRef INT NOT NULL,
	categoryRef INT NOT NULL,
	statusRef INT DEFAULT 1,
	subject VARCHAR(100) NOT NULL,
	content VARCHAR(2000) NOT NULL,
	price INT NOT NULL,
	latitude DOUBLE NOT NULL,
	longitude DOUBLE NOT NULL,
	location VARCHAR(200) NOT NULL,
	loc VARCHAR(200) NOT NULL,
	readCount INT DEFAULT 0,
	regDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT fk_daangn_board_ref FOREIGN KEY (userRef) REFERENCES daangn_member(idx) ON DELETE CASCADE
);


-- 중고마켓 사진
CREATE TABLE daangn_board_file (
    idx INT PRIMARY KEY AUTO_INCREMENT, 
    ref INT NOT NULL,
    originFileName VARCHAR(1000) NOT NULL,
    saveFileName VARCHAR(1000) NOT NULL,
    CONSTRAINT fk_daangn_board_file_ref FOREIGN KEY (ref) REFERENCES daangn_board(idx) ON DELETE CASCADE
);


-- 중고마켓 좋아요
CREATE TABLE daangn_like_tb(
	idx INT PRIMARY KEY AUTO_INCREMENT,
	userIdx INT NOT NULL,
	boardIdx INT NOT NULL,
	CONSTRAINT fk_daangn_like_tb_board_ref FOREIGN KEY (boardIdx) REFERENCES daangn_board(idx) ON DELETE CASCADE,
	CONSTRAINT fk_daangn_like_tb_member_ref FOREIGN KEY (userIdx) REFERENCES daangn_member(idx) ON DELETE CASCADE
);


-- 채팅방
CREATE TABLE chatRoom (
	roomIdx INT PRIMARY KEY AUTO_INCREMENT,
	userIdx INT NOT NULL,
	boardIdx INT NOT NULL,
	boardUserIdx INT NOT NULL,
	lastUpdateDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT fk_chatRoom_userIdx_ref FOREIGN KEY (userIdx) REFERENCES daangn_member(idx) ON DELETE CASCADE,
	CONSTRAINT fk_chatRoom_boardIdx_ref FOREIGN KEY (boardIdx) REFERENCES daangn_board(idx) ON DELETE CASCADE,
	CONSTRAINT fk_chatRoom_boardUserIdx_ref FOREIGN KEY (boardUserIdx) REFERENCES daangn_member(idx) ON DELETE CASCADE
);


-- 채팅메시지
CREATE TABLE chatMessage (
	idx INT PRIMARY KEY AUTO_INCREMENT,
	chatRoom INT NOT NULL,
	typeRef INT NOT NULL,
	sender INT NOT NULL,
	content VARCHAR(200) NOT NULL,
	regDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	readed INT DEFAULT 2,
	CONSTRAINT fk_chatMessage_chatRoom_ref FOREIGN KEY (chatRoom) REFERENCES chatRoom(roomIdx) ON DELETE CASCADE,
	CONSTRAINT fk_chatMessage_sender_ref FOREIGN KEY (sender) REFERENCES daangn_member(idx) ON DELETE CASCADE
);


-- 메시지 타입
CREATE TABLE MessageType (
    idx INT PRIMARY KEY AUTO_INCREMENT,
    typeStr VARCHAR(255) NOT NULL
);

INSERT INTO MessageType (typeStr) VALUES ('ENTER');
INSERT INTO MessageType (typeStr) VALUES ('TALK');
INSERT INTO MessageType (typeStr) VALUES ('LEAVE');
INSERT INTO MessageType (typeStr) VALUES ('RESERVE');


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


-- 예약
CREATE TABLE tb_reserve (
    idx INT PRIMARY KEY AUTO_INCREMENT,
    boardRef INT NOT NULL,
    userRef INT NOT NULL,
    interaction INT NOT NULL,
    CONSTRAINT fk_reserve_boardRef FOREIGN KEY (boardRef) REFERENCES daangn_board(idx) ON DELETE CASCADE,
    CONSTRAINT fk_reserve_userRef FOREIGN KEY (userRef) REFERENCES daangn_member(idx) ON DELETE CASCADE
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