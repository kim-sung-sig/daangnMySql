DROP TABLE daangn_board_file;
CREATE TABLE daangn_board_file (
    idx INT PRIMARY KEY AUTO_INCREMENT, 
    ref INT NOT NULL,
    originFileName VARCHAR(1000) NOT NULL,
    saveFileName VARCHAR(1000) NOT NULL,
    CONSTRAINT fk_daangn_board_file_ref FOREIGN KEY (ref) REFERENCES daangn_board(idx) ON DELETE CASCADE
);

SELECT * FROM daangn_board_file;