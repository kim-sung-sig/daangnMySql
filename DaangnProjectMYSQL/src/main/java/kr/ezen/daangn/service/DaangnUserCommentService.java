package kr.ezen.daangn.service;

import java.util.List;

import kr.ezen.daangn.vo.DaangnCommentVO;
import kr.ezen.daangn.vo.ScrollVO;

public interface DaangnUserCommentService {
	
	// 1. 유저평 쓰기
	int insertUserComment(DaangnCommentVO comment);
	
	// 2. 사용자의 유저평 조회하기
	List<DaangnCommentVO> getUserComments(ScrollVO sv);
	
	// 3. 사용자가 받은 유저평 갯수 얻기
	int getCountUserCommentsByUsedRef(int userRef);
	
	// 4. 유저테이블 최대갯수 얻기
	int getLastIdx();
	
	// 5. 유저평 한개 얻기(수정때 사용예정)
	DaangnCommentVO getUserCommentByIdx(int idx);
	
	// 6. 유저평 수정하기
	int updateComment(DaangnCommentVO comment);
	
	// 7. 유저평 삭제하기
	int deleteComment(int idx);
}
