DROP TABLE daangn_user_file;
CREATE TABLE daangn_user_file(
    idx INT PRIMARY KEY AUTO_INCREMENT,
    ref INT NOT NULL,
    originFileName VARCHAR(1000) NOT NULL,
    saveFileName VARCHAR(1000) NOT NULL,
    CONSTRAINT fk_daangn_user_file_ref FOREIGN KEY (ref) REFERENCES daangn_member(idx) ON DELETE CASCADE
);

SELECT * FROM daangn_user_file;
