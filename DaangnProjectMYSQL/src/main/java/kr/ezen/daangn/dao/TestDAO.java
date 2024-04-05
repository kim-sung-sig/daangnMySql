package kr.ezen.daangn.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import kr.ezen.daangn.vo.TestVO;

@Mapper
public interface TestDAO {
	TestVO selectByIdx(int idx) throws SQLException;
}
