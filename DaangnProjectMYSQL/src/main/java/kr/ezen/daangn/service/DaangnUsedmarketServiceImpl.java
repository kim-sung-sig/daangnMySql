package kr.ezen.daangn.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ezen.daangn.dao.DaangnBoardFileDAO;
import kr.ezen.daangn.dao.DaangnUsedmarketBoardDAO;
import kr.ezen.daangn.vo.DaangnMainBoardVO;
import kr.ezen.daangn.vo.DaangnUsedmarketBoardFileVO;
import kr.ezen.daangn.vo.ScrollVO;
import kr.ezen.daangn.vo.TestVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(value = "daangnUsedmarketService")
public class DaangnUsedmarketServiceImpl implements DaangnUsedmarketService{
	
	@Autowired
	private DaangnUsedmarketBoardDAO usedmarketBoardDAO;
	@Autowired
	private DaangnBoardFileDAO boardFileDAO;
	
	@Override
	public List<TestVO> selectPagedUsedmarketBoards(ScrollVO sv) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getBoardLastIdx() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public TestVO selectByIdx(int idx) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int insertUsedmarketBoard(TestVO testVO) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int updateUsedmarketBoard(TestVO testVO) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int deleteUsedmarketBoard(int idx) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int incrementReadCount(int idx) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int incrementLikeCount(int boardRef, int userRef) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int decrementLikeCount(int boardRef, int userRef) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int updateStatus(int boardRef, int statusRef) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<DaangnMainBoardVO> selectScrollListByUserIdx(ScrollVO sv) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<DaangnMainBoardVO> selectListByUserIdxAndNotBoardIdx(ScrollVO sv) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getBoardCountByUserIdxAndStatusRef(ScrollVO sv) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int isUsedmarketBoardLike(int userRef, int boardRef) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int insertUsedmarketBoardFile(DaangnUsedmarketBoardFileVO usedmarketBoardFileVO) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int deleteUsedmarketBoardFile(int boardRef) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
