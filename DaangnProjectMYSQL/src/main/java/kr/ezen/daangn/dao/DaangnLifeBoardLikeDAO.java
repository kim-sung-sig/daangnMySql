package kr.ezen.daangn.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DaangnLifeBoardLikeDAO {
	/**
	 * 좋아요하기 저장
	 * @param userRef
	 * @param boardRef
	 * @throws SQLException
	 */
	void insertLifeBoardLike(@Param("userRef") int userRef, @Param("boardRef") int boardRef) throws SQLException;
	
	/**
	 * 좋아요취소하기 삭제
	 * @param userRef
	 * @param boardRef
	 * @throws SQLException
	 */
	void deleteLifeBoardLike(@Param("userRef") int userRef, @Param("boardRef") int boardRef) throws SQLException;
	
	/**
	 * 좋아요했는지 확인하기
	 * @param userRef
	 * @param boardRef
	 * @return
	 * @throws SQLException
	 */
	int selectLifeBoardLike(@Param("userRef") int userRef, @Param("boardRef") int boardRef) throws SQLException;
}
