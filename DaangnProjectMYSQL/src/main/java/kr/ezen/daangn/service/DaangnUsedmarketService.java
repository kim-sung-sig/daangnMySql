package kr.ezen.daangn.service;

import java.util.List;

import kr.ezen.daangn.vo.DaangnUsedmarketBoardFileVO;
import kr.ezen.daangn.vo.DaangnUsedmarketBoardVO;
import kr.ezen.daangn.vo.DaangnUsedmarketChatMessageVO;
import kr.ezen.daangn.vo.DaangnUsedmarketChatRoomVO;
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
	int updateStatus(int boardRef, int statusRef);
	
	/**
	 * 유저가 쓴 글 주기 (lastItemIdx, sizeOfPage, userRef, statusRef)
	 * @param sv
	 * @return
	 */
	List<DaangnUsedmarketBoardVO> getUsedmarketBoardsByUserRef(ScrollVO sv);
	
	/**
	 * 유저의 판매상품의 상태에 따른 갯수 얻기 (userRef, statusRef)
	 * @param sv
	 * @return
	 */
	int getBoardCountByUserIdxAndStatusRef(ScrollVO sv);
	
	/**
	 * 게시글에 해당하는 유저의 다른 게시물 얻기 (userRef, boardRef)
	 * @param sv
	 * @return
	 */
	List<DaangnUsedmarketBoardVO> selectListByUserIdxAndNotBoardIdx(ScrollVO sv);
	
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
	
	//=========================================================================
	// 채팅관련
	//=========================================================================
	/**
	 * 채팅방 입장하기
	 * 1. 없으면 만들고 있으면 번호리턴
	 * 2. 상대방의 채팅을 모두 읽음 처리하기
	 * @param chatRoomVO
	 * @return 생성된 채팅방의 인덱스 or 기존 채팅방 인덱스
	 */
	int createChatRoom(int userRef, int boardRef);
	
	/**
	 * 1. 채팅방 목록 보기 (LastUpdateDate 기준)
	 * 2. 안읽은 채팅 메시지 수 가져오기(userRef, chatRoomRef) 채팅방에 해당
	 * @param userRef
	 * @return
	 */
	List<DaangnUsedmarketChatRoomVO> getChatRooms(int userRef);
	
    /**
     * 채팅방 삭제하기
     * @param chatRoomRef 채팅방 인덱스
     * @return 삭제된 채팅방 수
     */
    int deleteChatRoom(int chatRoomRef, Integer deleted1, Integer deleted2);

    /**
     * 1. 채팅메시지 저장
     * 2. 채팅방 LastUpdateDate를 업데이트
     * @param chatMessageVO
     */
    void insertMessage(DaangnUsedmarketChatMessageVO chatMessageVO);
    
    // 날라온 idx로 readed--
    void updateReadCount(int idx);
    
    // 안읽은 채팅 메시지 수 가져오기(userRef) 모든 채팅방의 총합
    int getUnReadCountByUserRef(int userRef);
    
 	/**
      * 채팅메시지 가져오기
      * @param sizeOfPage 한 페이지에 표시할 채팅메시지 수
      * @param lastItemIdx 마지막 채팅메시지 인덱스
      * @param chatRoomRef 채팅방 인덱스
      * @param deleted1, deleted1 누가 호출하는지
      * @return 채팅메시지 목록
      */
     List<DaangnUsedmarketChatMessageVO> getPagedChatMessages(
     		int lastItemIdx, int sizeOfPage, int chatRoomRef,
     		Integer deleted1, Integer deleted2
     	);
     
     /**
      * 채팅메시지 가장 큰 idx
      * @return
      */
     int getChatMessageLastIdx();
}
