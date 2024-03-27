DROP TABLE tb_visitor;
CREATE TABLE tb_visitor (
	idx INT PRIMARY KEY AUTO_INCREMENT,							-- 키필드
	visitIp VARCHAR(50) NOT NULL,									-- 접속자 ip
	visitTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL		-- 방문 시간
);
select * from tb_visitor ;

/*
public class VisitVO {
	private int idx;
	private String visitIp;			// 접속자 ip
	private Date visitTime;			// 방문 시간
}
*/