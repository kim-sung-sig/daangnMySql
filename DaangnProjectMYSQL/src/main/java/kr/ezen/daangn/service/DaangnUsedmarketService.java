package kr.ezen.daangn.service;

import java.util.List;

import kr.ezen.daangn.vo.DaangnMainBoardVO;
import kr.ezen.daangn.vo.DaangnUsedmarketBoardFileVO;
import kr.ezen.daangn.vo.ScrollVO;
import kr.ezen.daangn.vo.TestVO;

public interface DaangnUsedmarketService {
	
	/**
	 * 중고거래 게시글 목록 얻기 (lastItemIdx, sizeOfPage, categoryRef, region, gu, dong, search, userRef)
	 * @param sv
	 * @return
	 */
	List<TestVO> selectPagedUsedmarketBoards(ScrollVO sv);
	
	/**
	 * 중고거래 게시글 가장 큰 idx 얻기
	 * @return
	 */
	int getBoardLastIdx();
	
	/**
	 * 중고거래 게시글 한개 얻기
	 * @param idx
	 * @return
	 */
	TestVO selectByIdx(int idx);
	
	/**
	 * 중고거래 게시글 쓰기
	 * @param testVO
	 * @return
	 */
	int insertUsedmarketBoard(TestVO testVO);
	
	/**
	 * 중고거래 게시글 수정하기
	 * @param testVO
	 * @return
	 */
	int updateUsedmarketBoard(TestVO testVO);
	
	/**
	 * 중고거래 게시글 삭제하기
	 * @param idx
	 * @return
	 */
	int deleteUsedmarketBoard(int idx);
	
	/**
	 * 중고거래 게시글 조회수 증가
	 * @param idx
	 * @return
	 */
	int incrementReadCount(int idx);
	
	/**
	 * 중고거래 게시글 찜하기
	 * @param boardRef
	 * @param userRef
	 * @return
	 */
	int incrementLikeCount(int boardRef, int userRef);
	
	/**
	 * 중고거래 게시글 찜 취소하기
	 * @param boardRef
	 * @param userRef
	 * @return
	 */
	int decrementLikeCount(int boardRef, int userRef);
	
	/**
	 * 게시글 상태 변경하기
	 * @param boardRef
	 * @param statusRef
	 * @return
	 */
	int updateStatus(int boardRef, int statusRef);
	
	/**
	 * 유저가 쓴 글 주기 (lastItemIdx, sizeOfPage, userRef, statusRef)
	 * @param sv
	 * @return
	 */
	List<DaangnMainBoardVO> selectScrollListByUserIdx(ScrollVO sv);
	
	/**
	 * 게시글에 해당하는 유저의 다른 게시물 얻기 (userRef, boardRef)
	 * @param sv
	 * @return
	 */
	List<DaangnMainBoardVO> selectListByUserIdxAndNotBoardIdx(ScrollVO sv);
	
	/**
	 * 유저의 판매상품의 상태에 따른 갯수 얻기 (userRef, statusRef)
	 * @param sv
	 * @return
	 */
	int getBoardCountByUserIdxAndStatusRef(ScrollVO sv);
	
	/**
	 * 중고거래 게시물 좋아요 확인
	 * @param userRef
	 * @param boardRef
	 * @return
	 */
	int isUsedmarketBoardLike(int userRef, int boardRef);
	
	/**
	 * 중고거래 게시물 사진 저장
	 * @param usedmarketBoardFileVO
	 * @return
	 */
	int insertUsedmarketBoardFile(DaangnUsedmarketBoardFileVO usedmarketBoardFileVO);
	
	/**
	 * 중고거래 게시물 사진 삭제(게시글에 해당하는)
	 * @param boardRef
	 * @return
	 */
	int deleteUsedmarketBoardFile(int boardRef);
}
