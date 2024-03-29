DROP TABLE tb_reserve;
CREATE TABLE tb_reserve (
    idx INT PRIMARY KEY AUTO_INCREMENT,
    boardRef INT NOT NULL,
    userRef INT NOT NULL,
    interaction INT NOT NULL,
    CONSTRAINT fk_reserve_boardRef FOREIGN KEY (boardRef) REFERENCES daangn_board(idx) ON DELETE CASCADE,
    CONSTRAINT fk_reserve_userRef FOREIGN KEY (userRef) REFERENCES daangn_member(idx) ON DELETE CASCADE
);

SELECT * FROM TB_RESERVE tr ;