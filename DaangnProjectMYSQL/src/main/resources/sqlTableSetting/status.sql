DROP TABLE daangn_status ;
CREATE TABLE  daangn_status(
	statusIdx INT PRIMARY KEY AUTO_INCREMENT,
	statusName VARCHAR(100) NOT NULL
);
SELECT * FROM daangn_status;

INSERT INTO daangn_status (statusName) VALUES ('판매중'); -- 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_status (statusName) VALUES ('예약중'); -- 여기서 이름을 일단 미리 만들어 주자
INSERT INTO daangn_status (statusName) VALUES ('판매완료');