package kr.ezen.daangn.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ezen.daangn.vo.DaangnUsedmarketBoardVO;
import kr.ezen.daangn.vo.DaangnUsedmarketReserveVO;

@Mapper
public interface DaangnUsedmarketBoardReserveDAO {
	
	/**
	 * 예약하기 / 거래완료하기
	 * @param usedmarketReserveVO
	 * @throws SQLException
	 */
	void insertReserve(DaangnUsedmarketReserveVO usedmarketReserveVO) throws SQLException;
	
	/**
	 * 예약취소하기
	 * @param boardRef
	 * @throws SQLException
	 */
	void deleteReserveByboardRef(int boardRef) throws SQLException;
	
	/**
	 * 구매 목록 얻기 (lastItemIdx, sizeOfPage, userRef)
	 * @return
	 * @throws SQLException
	 */
	List<DaangnUsedmarketBoardVO> selectPurchaseListByUserRef(
			@Param("lastItemIdx") int lastItemIdx,
			@Param("sizeOfPage") int sizeOfPage,
			@Param("userRef") int userRef
		) throws SQLException;
	
	/**
	 * 구매 목록 갯수 얻기
	 * @param userRef
	 * @return
	 * @throws SQLException
	 */
	int getPurchaseListTotalCountByUserRef(int userRef) throws SQLException;
	
	/**
	 * 예약이 되어 있엇는지 확인 유저닉네임 리턴
	 * @param boardRef
	 * @return
	 * @throws SQLException
	 */
	String getReserveUserNickNameByBoardRef(int boardRef) throws SQLException;
}
