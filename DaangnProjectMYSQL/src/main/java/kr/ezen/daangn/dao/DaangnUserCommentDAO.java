package kr.ezen.daangn.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ezen.daangn.vo.DaangnCommentVO;

@Mapper
public interface DaangnUserCommentDAO {
	/**
	 * 유저평 저장하기
	 * @param comment
	 * @throws SQLException
	 */
	void insertComment(DaangnCommentVO comment) throws SQLException;
	
	/**
	 * 유저평 조회하기
	 * @return
	 * @throws SQLException
	 */
	List<DaangnCommentVO> getUserComments(@Param("lastItemIdx") int lastItemIdx,
										  @Param("sizeOfPage") int sizeOfPage,
										  @Param("userRef") int userRef
	) throws SQLException;
	
	/**
	 * 유저평 갯수 얻기
	 * @param userRef
	 * @return
	 * @throws SQLException
	 */
	int getCountUserCommentsByUsedRef(int userRef) throws SQLException;
	
	/**
	 * 최대 idx 얻기
	 * @return
	 * @throws SQLException
	 */
	int getLastIdx() throws SQLException;
	
	/**
	 * 유저평 한개 얻기
	 * @param idx
	 * @return
	 * @throws SQLException
	 */
	DaangnCommentVO getUserCommentByIdx(int idx) throws SQLException;
	
	/**
	 * 유저평 수정 하기
	 * @param comment
	 * @throws SQLException
	 */
	void updateComment(DaangnCommentVO comment) throws SQLException;
	
	/**
	 * 유저평 삭제하기
	 * @param idx
	 * @throws SQLException
	 */
	void deleteCommentByIdx(int idx) throws SQLException;
}
