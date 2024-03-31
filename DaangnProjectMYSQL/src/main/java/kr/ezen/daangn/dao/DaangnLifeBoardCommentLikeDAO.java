package kr.ezen.daangn.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DaangnLifeBoardCommentLikeDAO {
	/**
	 * 동네생활 댓글 좋아요하기 저장
	 * @param userRef
	 * @param boardRef
	 * @throws SQLException
	 */
	void insertLifeBoardCommentLike(@Param("userRef") int userRef, @Param("commentRef") int commentRef) throws SQLException;
	
	/**
	 * 동네생활 댓글 좋아요취소하기 삭제
	 * @param userRef
	 * @param boardRef
	 * @throws SQLException
	 */
	void deleteLifeBoardCommentLike(@Param("userRef") int userRef, @Param("commentRef") int commentRef) throws SQLException;
	
	/**
	 * 동네생활 댓글 좋아요했는지 확인하기
	 * @param userRef
	 * @param boardRef
	 * @return
	 * @throws SQLException
	 */
	int selectLifeBoardCommentLike(@Param("userRef") int userRef, @Param("commentRef") int commentRef) throws SQLException;
}
