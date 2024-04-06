package kr.ezen.daangn.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ezen.daangn.vo.DaangnUsedmarketBoardVO;

@Mapper
public interface DaangnUsedmarketBoardDAO {
	/**
	 * 중고거래 목록 얻기
	 * 1. 리스트 뿌릴때 (lastItemIdx, sizeOfPage, categoryRef, region, gu, dong, search)
	 * 2. 유저가쓴 목록 줄때 (lastItem, sizeOfPage, statusRef, userRef, boardRef)
	 * @param lastItemIdx
	 * @param sizeOfPage
	 * @param categoryRef
	 * @param statusRef
	 * @param region
	 * @param gu
	 * @param dong
	 * @param search
	 * @param userRef
	 * @param boardRef
	 * @return
	 * @throws SQLException
	 */
	List<DaangnUsedmarketBoardVO> selectPagedLifeBoards(
			@Param("lastItemIdx") int lastItemIdx,
			@Param("sizeOfPage") int sizeOfPage,
			@Param("categoryRef") Integer categoryRef,
			@Param("statusRef") Integer statusRef,
			@Param("region") String region,
			@Param("gu") String gu,
			@Param("dong") String dong,
			@Param("search") String search,
			@Param("userRef") Integer userRef,
			@Param("boardRef") Integer boardRef
		) throws SQLException;
	
	/**
	 * 최대 idx 얻기
	 * @return
	 * @throws SQLException
	 */
	int getLastIdx() throws SQLException;
	
	/**
	 * 중거거래 게시글 한개 얻기
	 * @param idx
	 * @return
	 * @throws SQLException
	 */
	DaangnUsedmarketBoardVO selectByIdx(int idx) throws SQLException;
	
	/**
	 * 중고거래 게시글 저장
	 * @param usedmarketBoardVO
	 * @throws SQLException
	 */
	void insertUsedmarketBoard(DaangnUsedmarketBoardVO usedmarketBoardVO) throws SQLException;

	/**
	 * 중고거래 게시글 수정
	 * @param usedmarketBoardVO
	 * @throws SQLException
	 */
	void updateUsedmarketBoard(DaangnUsedmarketBoardVO usedmarketBoardVO) throws SQLException;

	/**
	 * 중고거래 게시글 삭제
	 * @param idx
	 * @throws SQLException
	 */
	void deleteUsedmarketBoard(int idx) throws SQLException;
	
	/**
	 * 중고거래 조회수 증가
	 * @param idx
	 * @throws SQLException
	 */
	void incrementReadCount(int idx) throws SQLException;
	
	/**
	 * 중고거래 좋아요 수 증가
	 * @param idx
	 * @throws SQLException
	 */
	void incrementLikeCount(int idx) throws SQLException;
	
	/**
	 * 중고거래 좋아요 수 감소
	 * @param idx
	 * @throws SQLException
	 */
	void decrementLikeCount(int idx) throws SQLException;
	
	/**
	 * 중고거래 채팅 수 증가
	 * @param idx
	 * @throws SQLException
	 */
	void incrementChatRoomCount(int idx) throws SQLException;
	
	/**
	 * 중고거래 채팅 수 감소
	 * @param idx
	 * @throws SQLException
	 */
	void decrementChatRoomCount(int idx) throws SQLException;
	
	/**
	 * 중고거래 상태 변경하기
	 * @param idx
	 * @param statusRef
	 * @throws SQLException
	 */
	void updateUsedmarketBoardStatus(@Param("idx") int idx, @Param("statusRef") int statusRef) throws SQLException;
}
