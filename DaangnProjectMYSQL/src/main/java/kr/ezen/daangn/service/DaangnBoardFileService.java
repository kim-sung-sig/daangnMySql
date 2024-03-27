package kr.ezen.daangn.service;

import kr.ezen.daangn.vo.DaangnFileVO;

public interface DaangnBoardFileService {
	void insert(DaangnFileVO fileVO);
	void deleteByBoardIdx(int boardIdx);
}
