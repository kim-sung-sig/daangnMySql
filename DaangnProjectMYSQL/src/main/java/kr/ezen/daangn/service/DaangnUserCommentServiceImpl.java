package kr.ezen.daangn.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ezen.daangn.dao.DaangnUserCommentDAO;
import kr.ezen.daangn.vo.DaangnCommentVO;
import kr.ezen.daangn.vo.ScrollVO;

@Service("daangnUserCommentService")
public class DaangnUserCommentServiceImpl implements DaangnUserCommentService{
	
	@Autowired
	private DaangnUserCommentDAO commentDAO;
	
	/**
	 * 유저평 저장하기
	 */
	@Override
	public int insertUserComment(DaangnCommentVO comment) {
		int result = 0;
		try {
			commentDAO.insertComment(comment);
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 유저평 조회하기 (lastItemIdx, sizeOfPage, userRef)
	 */
	@Override
	public List<DaangnCommentVO> getUserComments(ScrollVO sv) {
		List<DaangnCommentVO> list = null;
		try {
			list = commentDAO.getUserComments(sv.getLastItemIdx(), sv.getSizeOfPage(), sv.getUserRef());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 유저의 유저평갯수 얻기
	 */
	@Override
	public int getCountUserCommentsByUsedRef(int userRef) {
		int result = 0;
		try {
			result = commentDAO.getCountUserCommentsByUsedRef(userRef);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 유저평 테이블 최대 idx 얻기
	 */
	@Override
	public int getLastIdx() {
		int result = 0;
		try {
			result = commentDAO.getLastIdx();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 유저평 한개 얻기(수정때 사용)
	 */
	@Override
	public DaangnCommentVO getUserCommentByIdx(int idx) {
		DaangnCommentVO comment = null;
		try {
			comment = commentDAO.getUserCommentByIdx(idx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comment;
	}
	
	/**
	 * 유저평 수정하기
	 */
	@Override
	public int updateComment(DaangnCommentVO comment) {
		int result = 0;
		try {
			commentDAO.updateComment(comment);
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 유저평 삭제하기
	 */
	@Override
	public int deleteComment(int idx) {
		int result = 0;
		try {
			commentDAO.deleteCommentByIdx(idx);
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
