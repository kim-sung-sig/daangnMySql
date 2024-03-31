package kr.ezen.daangn.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.ezen.daangn.vo.DaangnLifeBoardFileVO;

@Mapper
public interface DaangnLifeBoardFileDAO {
	/**
	 * 동네생활 게시글 사진 저장
	 * @param lifeBoardFileVO
	 * @throws SQLException
	 */
	void insertLifeBoardFile(DaangnLifeBoardFileVO lifeBoardFileVO) throws SQLException;
	
	/**
	 * 동네생활 게시글에 대한 사진 얻기
	 * @param boardRef
	 * @return List<DaangnLifeBoardFileVO>
	 * @throws SQLException
	 */
	List<DaangnLifeBoardFileVO> selectLifeBoardFileByBoardRef(int boardRef) throws SQLException;
	
	/**
	 * 동네생활 게시글에 대한 사진 삭제(수정때 사용)
	 * @param boardRef
	 * @return List<DaangnLifeBoardFileVO>
	 * @throws SQLException
	 */
	void deleteLifeBoardFileByBoardRef(int boardRef) throws SQLException;
}
