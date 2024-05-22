package kr.ezen.daangn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.ezen.daangn.dao.DaangnLifeBoardDAO;
import kr.ezen.daangn.vo.DaangnLifeBoardVO;

@SpringBootTest
class DaangnProjectApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private DaangnLifeBoardDAO lifeboardDAO;

	@Test
	void dbTest(){
		try {
			DaangnLifeBoardVO lifeBoardDetail = lifeboardDAO.selectByIdx(2);
			System.out.println(lifeBoardDetail);


			assertEquals(lifeBoardDetail.getFileList(), null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
