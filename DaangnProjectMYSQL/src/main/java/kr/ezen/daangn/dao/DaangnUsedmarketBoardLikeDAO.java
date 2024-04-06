package kr.ezen.daangn.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DaangnUsedmarketBoardLikeDAO {
	/**
	 * 중고거래 좋아요 저장
	 * @param usedmarketBoardLikeVO
	 * @throws SQLException
	 */
	void insertUsedmarketBoardLike(@Param("userRef") int userRef, @Param("boardRef") int boardRef) throws SQLException;
	
	/**
	 * 중고거래 좋아요 삭제
	 * @param userRef
	 * @param boardRef
	 * @throws SQLException
	 */
	void deleteUsedmarketBoardLike(@Param("userRef") int userRef, @Param("boardRef") int boardRef) throws SQLException;
	
	/**
	 * 중고거래 좋아요했는지 확인
	 * @param userRef
	 * @param boardRef
	 * @return
	 * @throws SQLException
	 */
	int selectUsedmarketBoardLike(@Param("userRef") int userRef, @Param("boardRef") int boardRef) throws SQLException;
}
