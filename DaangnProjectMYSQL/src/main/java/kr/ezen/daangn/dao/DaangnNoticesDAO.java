package kr.ezen.daangn.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.ezen.daangn.vo.NoticesVO;

@Mapper
public interface DaangnNoticesDAO {
	
	/** 페이지 리스트 얻기 */
	List<NoticesVO> selectList(HashMap<String, Object> map) throws SQLException;
	
	/** 전체갯수 얻기 */
	int selectCount() throws SQLException;
	
	/** 하나 얻기*/
	NoticesVO selectByIdx(int idx) throws SQLException;
	
	/** 공지사항 저장하기 */
	void insert(NoticesVO nv) throws SQLException;
	
	/** 공지사항 수정하기 (idx, title, content, highlight) */
	void update(NoticesVO nv) throws SQLException;
	
	/** 고정 공지 얻기 */
	List<NoticesVO> selectByHighlight() throws SQLException;
	
	/** 삭제하기 */
	void deleteByIdx(int idx) throws SQLException;
}
