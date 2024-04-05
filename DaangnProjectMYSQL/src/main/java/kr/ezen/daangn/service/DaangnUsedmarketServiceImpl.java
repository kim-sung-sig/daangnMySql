package kr.ezen.daangn.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ezen.daangn.dao.DaangnBoardFileDAO;
import kr.ezen.daangn.dao.TestDAO;
import kr.ezen.daangn.vo.TestVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(value = "daangnUsedmarketService")
public class DaangnUsedmarketServiceImpl {
	
	@Autowired
	private TestDAO testDAO;
	@Autowired
	private DaangnBoardFileDAO boardFileDAO;
	
	/**
	 * 중고거래 게시글 하나 얻기
	 * @param idx
	 * @return
	 */
	public TestVO selectByIdx(int idx) {
		log.info("selectByIdx 실행 idx => {}", idx);
		TestVO testVO = null;
		try {
			testVO = testDAO.selectByIdx(idx);
			if(testVO != null) {
				testVO.setBoardFileList(boardFileDAO.selectFileByBoardIdx(testVO.getIdx()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("selectByIdx 리턴 {}", testVO);
		return testVO;
	}
	
	
}
