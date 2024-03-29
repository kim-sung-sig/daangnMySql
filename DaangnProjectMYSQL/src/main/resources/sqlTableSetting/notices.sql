DROP table notices;
CREATE TABLE notices(
	idx INT PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(500) NOT NULL,
	content TEXT NOT NULL,
	regDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	highlight INT DEFAULT 0
);

select * from notices;