DROP TABLE daangn_comment;
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

SELECT * FROM daangn_comment;