package kr.ezen.daangn.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ezen.daangn.vo.DaangnLifeCommentVO;

@Mapper
public interface DaangnLifeBoardCommentDAO {
	
	/**
	 * 동네생활 게시물의 해당하는 댓글 목록조회
	 * @param lastItemIdx
	 * @param sizeOfPage
	 * @param boardRef
	 * @return
	 * @throws SQLException
	 */
	List<DaangnLifeCommentVO> selectPagedLifeBoardComments(
			@Param("lastItemIdx") int lastItemIdx,
			@Param("sizeOfPage") int sizeOfPage,
			@Param("boardRef") int boardRef
		) throws SQLException;
	
	/**
	 * 동네생활 댓글에 해당하는 자식댓글 조회
	 * @param commentRef
	 * @return
	 * @throws SQLException
	 */
	List<DaangnLifeCommentVO> selectLifeBoardChildComments(int commentRef) throws SQLException;
	
	/**
	 * 댓글 한개 얻기
	 * @param idx
	 * @return
	 * @throws SQLException
	 */
	DaangnLifeCommentVO selectCommentByIdx(int idx) throws SQLException;
	
	/**
	 * 최대 idx 얻기
	 * @return
	 * @throws SQLException
	 */
	int getLastIdx() throws SQLException;
	
	/**
	 * 동네생활 댓글 저장
	 * @param lifeCommentVO
	 * @throws SQLException
	 */
	void insertLifeBoardComment(DaangnLifeCommentVO lifeCommentVO) throws SQLException;
	
	/**
	 * 동네생활 댓글 수정
	 * @param lifeCommentVO
	 * @throws SQLException
	 */
	void updateLifeBoardComment(DaangnLifeCommentVO lifeCommentVO) throws SQLException;
	
	/**
	 * 동네생활 댓글 삭제
	 * @param idx
	 * @throws SQLException
	 */
	void deleteLifeBoardComment(int idx) throws SQLException;
	
	/**
	 * 동네생활 댓글 좋아요 수 증가
	 * @param idx
	 * @throws SQLException
	 */
	void incrementLikeCount(int idx) throws SQLException;
	
	/**
	 * 동네생활 댓글 좋아요 수 감소
	 * @param idx
	 * @throws SQLException
	 */
	void decrementLikeCount(int idx) throws SQLException;
	
	/**
	 * 동네생활 댓글 대댓글 수 증가
	 * @param idx
	 * @throws SQLException
	 */
	void incrementChildCommentCount(int idx) throws SQLException;
	
	/**
	 * 동네생활 댓글 대댓글 수 감소
	 * @param idx
	 * @throws SQLException
	 */
	void decrementChildCommentCount(int idx) throws SQLException;
}
