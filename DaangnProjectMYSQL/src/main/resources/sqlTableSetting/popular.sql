DROP TABLE tb_popular;
CREATE TABLE tb_popular(
	idx INT PRIMARY KEY AUTO_INCREMENT,
	boardRef INT NOT NULL,
	userRef INT NOT NULL,
	interaction INT NOT NULL,
	interaction_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT fk_tb_popular_board_ref FOREIGN KEY (boardRef) REFERENCES daangn_board(idx) ON DELETE CASCADE,
	CONSTRAINT fk_tb_popular_member_ref FOREIGN KEY (userRef) REFERENCES daangn_member(idx) ON DELETE CASCADE	
);

select * from tb_popular;