package kr.ezen.daangn.service;

import java.util.List;

import kr.ezen.daangn.vo.DaangnUsedmarketBoardFileVO;
import kr.ezen.daangn.vo.DaangnUsedmarketBoardVO;
import kr.ezen.daangn.vo.DaangnUsedmarketReserveVO;
import kr.ezen.daangn.vo.ScrollVO;

public interface DaangnUsedmarketService {
	
	/**
	 * 중고거래 게시글 목록 얻기 (lastItemIdx, sizeOfPage, categoryRef, region, gu, dong, search, userRef)
	 * @param sv
	 * @return
	 */
	List<DaangnUsedmarketBoardVO> getUsedmarketBoards(ScrollVO sv);
	
	/**
	 * 중고거래 게시글 가장 큰 idx 얻기
	 * @return
	 */
	int getBoardLastIdx();
	

	/**
	 * 유저의 판매상품의 상태에 따른 갯수 얻기 (userRef, statusRef)
	 * @param sv
	 * @return
	 */
	int getBoardCountBy(int userRef, int statusRef);
	
	/**
	 * 검색 상태에 따른 갯수 얻기 (categoryRef, region, gu, dong, search)
	 * @param sv
	 * @return
	 */
	int getBoardCountBy(Integer categoryRef, String region, String gu, String dong, String search);
	
	/**
	 * 중고거래 게시글 한개 얻기
	 * @param idx
	 * @return
	 */
	DaangnUsedmarketBoardVO selectByIdx(int idx);
	
	/**
	 * 중고거래 게시글 쓰기
	 * @param testVO
	 * @return
	 */
	int insertUsedmarketBoard(DaangnUsedmarketBoardVO boardVO);
	
	/**
	 * 중고거래 게시글 수정하기
	 * @param testVO
	 * @return
	 */
	int updateUsedmarketBoard(DaangnUsedmarketBoardVO boardVO);
	
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
	 * 게시글 상태 변경하기
	 * @param boardRef
	 * @param statusRef
	 * @return
	 */
	int updateStatus(int boardRef, Integer userRef, int statusRef);
	
	/**
	 * 게시글에 해당하는 유저의 다른 게시물 얻기 (userRef, boardRef)
	 * @param sv
	 * @return
	 */
	List<DaangnUsedmarketBoardVO> selectListByUserIdxAndNotBoardIdx(int userRef, int boardRef);
	
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
	
	//=========================================================================
	// 좋아요 관련
	//=========================================================================
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
	 * 중고거래 게시물 좋아요 확인
	 * @param userRef
	 * @param boardRef
	 * @return
	 */
	int isUsedmarketBoardLike(int userRef, int boardRef);
	
	/**
	 * 좋아요 한 목록 얻기
	 * @param sv
	 * @return
	 */
	List<DaangnUsedmarketBoardVO> getLikeBoardsByUserRef(ScrollVO sv);
	
	/**
	 * 좋아요 한 목록 총합 얻기
	 * @param userRef
	 * @return
	 */
	int getLikeBoardsSizeByUserRef(int userRef);
	
	/**
	 * 좋아요 최대 idx 얻기
	 * @return
	 */
	int getLikeLastItemIdx();
	
	
	//=========================================================================
	// 예약 관련
	//=========================================================================
	/**
	 * 예약자가 있는지 확인하기
	 * @param boardRef
	 * @return
	 */
	DaangnUsedmarketReserveVO getReserveByBoardRef(int boardRef);
	
	/**
	 * 구매 목록 얻기
	 * @param sv
	 * @return
	 */
	List<DaangnUsedmarketBoardVO> getPurchaseListByUserRef(ScrollVO sv);
	
	/**
	 * 구매 목록 총합 얻기
	 * @param userRef
	 * @return
	 */
	int getPurchaseListSizeByUserRef(int userRef);
	
	/**
	 * 구매목록 최대 idx 얻기
	 * @return
	 */
	int getReserveLastItemIdx();
}
