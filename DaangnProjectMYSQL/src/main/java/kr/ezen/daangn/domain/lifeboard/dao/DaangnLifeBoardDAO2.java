package kr.ezen.daangn.domain.lifeboard.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import kr.ezen.daangn.domain.lifeboard.vo.DaangnLifeBoardDetail;

@Mapper
public interface DaangnLifeBoardDAO2 {
    
	DaangnLifeBoardDetail selectByIdx(int idx) throws SQLException;
}
