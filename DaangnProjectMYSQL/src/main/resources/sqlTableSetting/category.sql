DROP TABLE daangn_category ;
CREATE TABLE  daangn_category(
	categoryIdx INT PRIMARY KEY AUTO_INCREMENT,
	categoryName VARCHAR(100) NOT NULL
);

SELECT * FROM daangn_category;

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