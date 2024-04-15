package kr.ezen.daangn.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ezen.daangn.vo.DaangnLifeBoardVO;

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

	/**
	 * 동네생활 좋아요 최대 idx 얻기
	 * @return
	 * @throws SQLException
	 */
	int getLastIdx() throws SQLException;

	/**
	 * 동네생활 유저가 좋아한 글 총합
	 * @param userRef
	 * @return
	 * @throws SQLException
	 */
	int getTotalCountByUserRef(@Param("userRef") int userRef) throws SQLException;

	/**
	 * 동네생활 유저가 좋아한 글 목록 얻기
	 * @param lastItemIdx
	 * @param sizeOfPage
	 * @param userRef
	 * @return
	 * @throws SQLException
	 */
	List<DaangnLifeBoardVO> selectLikedBoardsByUserRef(
		@Param("lastItemIdx") int lastItemIdx,
		@Param("sizeOfPage") int sizeOfPage,
		@Param("userRef") int userRef
	) throws SQLException;
}
