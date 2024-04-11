package kr.ezen.daangn.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ezen.daangn.vo.DaangnUsedmarketBoardVO;

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
	
	/**
	 * 마이페이지에서 좋아요한 목록 조회
	 * @param lastItemIdx
	 * @param sizeOfPage
	 * @param userRef
	 * @return
	 * @throws SQLException
	 */
	List<DaangnUsedmarketBoardVO> selectLikeBoardsByUserRef(@Param("lastItemIdx") int lastItemIdx, @Param("sizeOfPage") int sizeOfPage, @Param("userRef") int userRef) throws SQLException;
	
	/**
	 * 마이페이지 좋아요한 목록 총합
	 * @param userRef
	 * @return
	 * @throws SQLException
	 */
	int getLikeBoardTotalCountByUserRef(int userRef) throws SQLException;
	
	int getLastItemIdx() throws SQLException;
}
