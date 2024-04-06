package kr.ezen.daangn.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.ezen.daangn.vo.DaangnUsedmarketBoardFileVO;

@Mapper
public interface DaangnUsedmarketBoardFileDAO {
	/**
	 * 중고거래 사진 저장하기
	 * @param usedmarketBoardFileVO
	 * @throws SQLException
	 */
	void insertUsedmarketBoardFile(DaangnUsedmarketBoardFileVO usedmarketBoardFileVO) throws SQLException;
	
	/**
	 * 중고거래 게시글에 대한 사진 얻기
	 * @param boardRef
	 * @return List<DaangnUsedmarketBoardFileVO>
	 * @throws SQLException
	 */
	List<DaangnUsedmarketBoardFileVO> selectUsedmarketBoardFileByBoardRef(int boardRef) throws SQLException;

	/**
	 * 중고거래 게시글에 대한 사진 삭제(수정때 사용)
	 * @param boardRef
	 * @throws SQLException
	 */
	void deleteUsedmarketBoardFileByBoardRef(int boardRef) throws SQLException;
}
