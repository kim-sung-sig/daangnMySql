show tables;

DROP TABLE IF EXISTS daangn_usedmarket_board_reserve;
DROP TABLE IF EXISTS daangn_usedmarket_board_file;
DROP TABLE IF EXISTS daangn_usedmarket_board_like;
DROP TABLE IF EXISTS daangn_usedmarket_board_chatMessage;
DROP TABLE IF EXISTS daangn_usedmarket_board_chatRoom;
DROP TABLE IF EXISTS daangn_usedmarket_board;
DROP TABLE IF EXISTS daangn_usedmarket_status;
DROP TABLE IF EXISTS daangn_usedmarket_category;

-- daangn_usedmarket_board 테이블에 데이터 삽입
INSERT INTO daangn_usedmarket_board
    (
        userRef,
        categoryRef,
        thumbnail,
        title, 
        content,
        price,
        latitude, 
        longitude,
        loc1, 
        loc2, 
        loc3, 
        location,
        ip
    )
SELECT
    6,
    1,
    '0626a02a-fd0a-4679-b00c-8d949633e3ca_다운로드 (18).jfif',
    CONCAT('제목 ', seq.seq),
    CONCAT('내용 ', seq.seq),
    10000,
    37.54093868750438, 
    126.83889505563894,
    '서울특별시', 
    '강서구', 
    '화곡3동', 
    '테스트', 
    '192.168.0.1'
FROM (
    SELECT 
        (thousands.thousands + hundreds.hundreds + tens.tens + units.units) + 1 AS seq
    FROM 
        (SELECT 0 AS units UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS units,
        (SELECT 0 AS tens UNION ALL SELECT 10 UNION ALL SELECT 20 UNION ALL SELECT 30 UNION ALL SELECT 40 UNION ALL SELECT 50 UNION ALL SELECT 60 UNION ALL SELECT 70 UNION ALL SELECT 80 UNION ALL SELECT 90) AS tens,
        (SELECT 0 AS hundreds UNION ALL SELECT 100 UNION ALL SELECT 200 UNION ALL SELECT 300 UNION ALL SELECT 400 UNION ALL SELECT 500 UNION ALL SELECT 600 UNION ALL SELECT 700 UNION ALL SELECT 800 UNION ALL SELECT 900) AS hundreds,
        (SELECT 0 AS thousands UNION ALL SELECT 1000 UNION ALL SELECT 2000 UNION ALL SELECT 3000 UNION ALL SELECT 4000 UNION ALL SELECT 5000 UNION ALL SELECT 6000 UNION ALL SELECT 7000 UNION ALL SELECT 8000 UNION ALL SELECT 9000) AS thousands
) seq;

-- daangn_usedmarket_board_file 테이블에 데이터 삽입
INSERT INTO daangn_usedmarket_board_file (boardRef, saveFileName)
SELECT 
    idx, 
    '0626a02a-fd0a-4679-b00c-8d949633e3ca_다운로드 (18).jfif'
FROM 
    daangn_usedmarket_board,
    (
        SELECT 
            (thousands.thousands + hundreds.hundreds + tens.tens + units.units) + 1 AS seq
        FROM 
            (SELECT 0 AS units UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) units,
            (SELECT 0 AS tens UNION ALL SELECT 10 UNION ALL SELECT 20 UNION ALL SELECT 30 UNION ALL SELECT 40 UNION ALL SELECT 50 UNION ALL SELECT 60 UNION ALL SELECT 70 UNION ALL SELECT 80 UNION ALL SELECT 90) tens,
            (SELECT 0 AS hundreds UNION ALL SELECT 100 UNION ALL SELECT 200 UNION ALL SELECT 300 UNION ALL SELECT 400 UNION ALL SELECT 500 UNION ALL SELECT 600 UNION ALL SELECT 700 UNION ALL SELECT 800 UNION ALL SELECT 900) hundreds,
            (SELECT 0 AS thousands UNION ALL SELECT 1000 UNION ALL SELECT 2000 UNION ALL SELECT 3000 UNION ALL SELECT 4000 UNION ALL SELECT 5000 UNION ALL SELECT 6000 UNION ALL SELECT 7000 UNION ALL SELECT 8000 UNION ALL SELECT 9000) thousands
    ) seq
WHERE 
    daangn_usedmarket_board.idx = seq.seq;
   
   
INSERT INTO daangn_life_board
    (
        userRef,
        categoryRef,
        title,
        content,
        loc1,
        loc2,
        loc3,
        ip
    )
SELECT
    6,
    1,
    CONCAT('제목 ', seq.seq),
    CONCAT('내용 ', seq.seq),
    '서울특별시', 
    '강서구', 
    '화곡3동', 
    '192.168.0.1'
FROM (
    SELECT 
        (thousands.thousands + hundreds.hundreds + tens.tens + units.units) + 1 AS seq
    FROM 
        (SELECT 0 AS units UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) units,
        (SELECT 0 AS tens UNION ALL SELECT 10 UNION ALL SELECT 20 UNION ALL SELECT 30 UNION ALL SELECT 40 UNION ALL SELECT 50 UNION ALL SELECT 60 UNION ALL SELECT 70 UNION ALL SELECT 80 UNION ALL SELECT 90) tens,
        (SELECT 0 AS hundreds UNION ALL SELECT 100 UNION ALL SELECT 200 UNION ALL SELECT 300 UNION ALL SELECT 400 UNION ALL SELECT 500 UNION ALL SELECT 600 UNION ALL SELECT 700 UNION ALL SELECT 800 UNION ALL SELECT 900) hundreds,
        (SELECT 0 AS thousands UNION ALL SELECT 1000 UNION ALL SELECT 2000 UNION ALL SELECT 3000 UNION ALL SELECT 4000 UNION ALL SELECT 5000 UNION ALL SELECT 6000 UNION ALL SELECT 7000 UNION ALL SELECT 8000 UNION ALL SELECT 9000) thousands
) seq;


INSERT INTO daangn_life_board_file (boardRef, saveFileName)
SELECT 
    idx, 
    CONCAT('0626a02a-fd0a-4679-b00c-8d949633e3ca_다운로드 (18).jfif')
FROM 
    daangn_life_board,
    (
        SELECT 
            (thousands.thousands + hundreds.hundreds + tens.tens + units.units) + 1 AS seq
        FROM 
            (SELECT 0 AS units UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) units,
            (SELECT 0 AS tens UNION ALL SELECT 10 UNION ALL SELECT 20 UNION ALL SELECT 30 UNION ALL SELECT 40 UNION ALL SELECT 50 UNION ALL SELECT 60 UNION ALL SELECT 70 UNION ALL SELECT 80 UNION ALL SELECT 90) tens,
            (SELECT 0 AS hundreds UNION ALL SELECT 100 UNION ALL SELECT 200 UNION ALL SELECT 300 UNION ALL SELECT 400 UNION ALL SELECT 500 UNION ALL SELECT 600 UNION ALL SELECT 700 UNION ALL SELECT 800 UNION ALL SELECT 900) hundreds,
            (SELECT 0 AS thousands UNION ALL SELECT 1000 UNION ALL SELECT 2000 UNION ALL SELECT 3000 UNION ALL SELECT 4000 UNION ALL SELECT 5000 UNION ALL SELECT 6000 UNION ALL SELECT 7000 UNION ALL SELECT 8000 UNION ALL SELECT 9000) thousands
    ) seq
WHERE 
    daangn_life_board.idx = seq.seq
    AND seq.seq % 3 != 0;
   
   
UPDATE daangn_usedmarket_board
SET statusRef = 3
WHERE idx % 3 = 1;

INSERT INTO daangn_usedmarket_board_reserve (boardRef, userRef, interaction)
select
	idx, 7, 1
FROM daangn_usedmarket_board
WHERE idx % 3 = 1;

select * from daangn_usedmarket_board_reserve order by idx desc;

select count(*) from daangn_life_board ;
select * from daangn_life_board;
select * from daangn_life_board_file ;
select count(*) from daangn_life_board_file ;
   

SELECT count(*) FROM daangn_usedmarket_board_file;
SELECT * FROM daangn_usedmarket_board_file;
SELECT count(*) FROM daangn_usedmarket_board;
SELECT * FROM daangn_usedmarket_board order by idx desc;
update daangn_usedmarket_board set latitude = 37.54093868750438, longitude = 126.83889505563894;




--- 1

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
	thumbnail VARCHAR(500) NOT NULL,
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
	likeCount INT DEFAULT 0,
	chatRoomCount INT DEFAULT 0,
	createDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updateDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	ip VARCHAR(200) NOT NULL,
	CONSTRAINT fk_dub_userRef FOREIGN KEY (userRef) REFERENCES daangn_member(idx) ON DELETE CASCADE,
	CONSTRAINT fk_dub_stRef FOREIGN KEY (categoryRef) REFERENCES daangn_usedmarket_category(categoryIdx) ON DELETE CASCADE,
	CONSTRAINT fk_dub_ctRef FOREIGN KEY (statusRef) REFERENCES daangn_usedmarket_status(statusIdx) ON DELETE CASCADE
);

-- 중고마켓 사진
CREATE TABLE daangn_usedmarket_board_file(
    idx INT PRIMARY KEY AUTO_INCREMENT, 
    boardRef INT NOT NULL,
    saveFileName VARCHAR(1000) NOT NULL,
    CONSTRAINT fk_dubf_ref FOREIGN KEY (boardRef) REFERENCES daangn_usedmarket_board(idx) ON DELETE CASCADE
);


-- 중고마켓 좋아요
CREATE TABLE daangn_usedmarket_board_like(
	idx INT PRIMARY KEY AUTO_INCREMENT,
	userRef INT NOT NULL,
	boardRef INT NOT NULL,
	CONSTRAINT fk_dubl_boardRef FOREIGN KEY (boardRef) REFERENCES daangn_usedmarket_board(idx) ON DELETE CASCADE,
	CONSTRAINT fk_dubl_userRef FOREIGN KEY (userRef) REFERENCES daangn_member(idx) ON DELETE CASCADE
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
	isActivate INT DEFAULT 1,
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
	createDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	readed INT DEFAULT 2,
	deleted1 INT DEFAULT 0, -- userRef 삭제했는지.. 삭제 1
	deleted2 INT DEFAULT 0, -- boardUserRef 삭제했는지.. 삭제 1
	CONSTRAINT fk_dubcm_chatRoomRef FOREIGN KEY (chatRoomRef) REFERENCES daangn_usedmarket_board_chatRoom(roomIdx) ON DELETE CASCADE,
	CONSTRAINT fk_dubcm_sender_userRef FOREIGN KEY (sender) REFERENCES daangn_member(idx) ON DELETE CASCADE
);

-- 예약 테이블
CREATE TABLE daangn_usedmarket_board_reserve(
    idx INT PRIMARY KEY AUTO_INCREMENT,
    boardRef INT NOT NULL,
    userRef INT NOT NULL,
    reserveDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
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



-- 2

