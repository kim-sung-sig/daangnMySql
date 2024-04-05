show tables;

DROP TABLE IF EXISTS daangn_usedmarket_board_reserve;
DROP TABLE IF EXISTS daangn_usedmarket_board_file;
DROP TABLE IF EXISTS daangn_usedmarket_board_like;
DROP TABLE IF EXISTS daangn_usedmarket_board_chatMessage;
DROP TABLE IF EXISTS daangn_usedmarket_board_chatRoom;
DROP TABLE IF EXISTS daangn_usedmarket_board;
DROP TABLE IF EXISTS daangn_usedmarket_status;
DROP TABLE IF EXISTS daangn_usedmarket_category;

-- 상품상태
CREATE TABLE  daangn_usedmarket_status(
	statusIdx INT PRIMARY KEY AUTO_INCREMENT,
	statusName VARCHAR(100) NOT NULL
);

INSERT INTO daangn_usedmarket_status (statusName) VALUES ('판매중'); -- 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_usedmarket_status (statusName) VALUES ('예약중'); -- 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_usedmarket_status (statusName) VALUES ('판매완료');

-- 중고 카테고리
CREATE TABLE  daangn_usedmarket_category(
	categoryIdx INT PRIMARY KEY AUTO_INCREMENT,
	categoryName VARCHAR(100) NOT NULL
);

INSERT INTO daangn_usedmarket_category (categoryName) VALUES ('디지털/가전');			-- 1 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_usedmarket_category (categoryName) VALUES ('가구/인테리어');		-- 2 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_usedmarket_category (categoryName) VALUES ('유아동/유아도서');		-- 3 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_usedmarket_category (categoryName) VALUES ('생활/가공식품');		-- 4 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_usedmarket_category (categoryName) VALUES ('스포츠/레저');			-- 5 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_usedmarket_category (categoryName) VALUES ('여성잡화');			-- 6 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_usedmarket_category (categoryName) VALUES ('여성의류');			-- 7 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_usedmarket_category (categoryName) VALUES ('남성패션/잡화');		-- 8 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_usedmarket_category (categoryName) VALUES ('게임/취미');			-- 9 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_usedmarket_category (categoryName) VALUES ('뷰티/미용');			-- 10 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_usedmarket_category (categoryName) VALUES ('반려동물용품');		-- 11 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_usedmarket_category (categoryName) VALUES ('도서/티켓/음반');		-- 12 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_usedmarket_category (categoryName) VALUES ('기타'); 				-- 13 여기서 이름을 일단 미리 만들어 주자

CREATE TABLE daangn_usedmarket_board(
	idx INT PRIMARY KEY AUTO_INCREMENT,
	userRef INT NOT NULL,
	categoryRef INT NOT NULL,
	statusRef INT DEFAULT 1,
	title VARCHAR(100) NOT NULL,
	content VARCHAR(2000) NOT NULL,
	price INT NOT NULL,
	latitude DOUBLE NOT NULL,
	longitude DOUBLE NOT NULL,
	loc1 VARCHAR(200) NOT NULL,
	loc2 VARCHAR(200) NOT NULL,
	loc3 VARCHAR(200) NOT NULL,
	location VARCHAR(200) NOT NULL,
	readCount INT DEFAULT 0,
	regDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT fk_dub_userRef FOREIGN KEY (userRef) REFERENCES daangn_member(idx) ON DELETE CASCADE,
	CONSTRAINT fk_dub_stRef FOREIGN KEY (categoryRef) REFERENCES daangn_usedmarket_category(categoryIdx) ON DELETE CASCADE,
	CONSTRAINT fk_dub_ctRef FOREIGN KEY (statusRef) REFERENCES daangn_usedmarket_status(statusIdx) ON DELETE CASCADE
);

-- 중고마켓 사진
CREATE TABLE daangn_usedmarket_board_file(
    idx INT PRIMARY KEY AUTO_INCREMENT, 
    ref INT NOT NULL,
    saveFileName VARCHAR(1000) NOT NULL,
    CONSTRAINT fk_dubf_ref FOREIGN KEY (ref) REFERENCES daangn_usedmarket_board(idx) ON DELETE CASCADE
);


-- 중고마켓 좋아요
CREATE TABLE daangn_usedmarket_board_like(
	userRef INT NOT NULL,
	boardRef INT NOT NULL,
	CONSTRAINT fk_dubl_userRef FOREIGN KEY (userRef) REFERENCES daangn_usedmarket_board(idx) ON DELETE CASCADE,
	CONSTRAINT fk_dubl_boardRef FOREIGN KEY (boardRef) REFERENCES daangn_member(idx) ON DELETE CASCADE
);

-- 채팅방
CREATE TABLE daangn_usedmarket_board_chatRoom(
	roomIdx INT PRIMARY KEY AUTO_INCREMENT,
	userRef INT NOT NULL,
	boardRef INT NOT NULL,
	boardUserRef INT NOT NULL,
	lastUpdateDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	deleted1 INT DEFAULT 0, -- userRef 삭제했는지.. 삭제 1
	deleted2 INT DEFAULT 0, -- boardUserRef 삭제했는지.. 삭제 1
	CONSTRAINT fk_dubcr_userRef FOREIGN KEY (userRef) REFERENCES daangn_member(idx) ON DELETE CASCADE,
	CONSTRAINT fk_dubcr_boardRef FOREIGN KEY (boardRef) REFERENCES daangn_usedmarket_board(idx) ON DELETE CASCADE,
	CONSTRAINT fk_dubcr_boardUserRef FOREIGN KEY (boardUserRef) REFERENCES daangn_member(idx) ON DELETE CASCADE
);

-- 채팅메시지
CREATE TABLE daangn_usedmarket_board_chatMessage(
	idx INT PRIMARY KEY AUTO_INCREMENT,
	chatRoomRef INT NOT NULL,
	typeRef INT NOT NULL, -- 사진 전송 또는 위치 전송
	sender INT NOT NULL,
	content VARCHAR(200) NOT NULL,
	regDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	readed INT DEFAULT 2,
	deleted1 INT DEFAULT 0, -- userRef 삭제했는지.. 삭제 1
	deleted2 INT DEFAULT 0, -- boardUserRef 삭제했는지.. 삭제 1
	CONSTRAINT fk_dubcm_chatRoomRef FOREIGN KEY (chatRoomRef) REFERENCES daangn_usedmarket_board_chatRoom(roomIdx) ON DELETE CASCADE,
	CONSTRAINT fk_dubcm_sender_userRef FOREIGN KEY (sender) REFERENCES daangn_member(idx) ON DELETE CASCADE
);

CREATE TABLE daangn_usedmarket_board_reserve(
    idx INT PRIMARY KEY AUTO_INCREMENT,
    boardRef INT NOT NULL,
    userRef INT NOT NULL,
    interaction INT NOT NULL,
    CONSTRAINT fk_dubr_boardRef FOREIGN KEY (boardRef) REFERENCES daangn_usedmarket_board(idx) ON DELETE CASCADE,
    CONSTRAINT fk_dubr_userRef FOREIGN KEY (userRef) REFERENCES daangn_member(idx) ON DELETE CASCADE
);

SELECT * FROM daangn_usedmarket_board_reserve;
SELECT * FROM daangn_usedmarket_board_like;
SELECT * FROM daangn_usedmarket_board_file;
SELECT * FROM daangn_usedmarket_board_chatRoom;
SELECT * FROM daangn_usedmarket_board_chatMessage;
SELECT * FROM daangn_usedmarket_board;