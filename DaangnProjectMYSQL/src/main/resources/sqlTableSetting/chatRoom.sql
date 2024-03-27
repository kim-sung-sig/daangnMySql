DROP TABLE chatRoom;
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

SELECT * FROM CHATROOM c ;