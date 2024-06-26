DROP TABLE DAANGN_BOARD ;
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

SELECT * FROM daangn_board;