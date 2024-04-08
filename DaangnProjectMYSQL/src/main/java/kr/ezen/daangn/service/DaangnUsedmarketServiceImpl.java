package kr.ezen.daangn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ezen.daangn.dao.DaangnUsedmarketBoardChatMessageDAO;
import kr.ezen.daangn.dao.DaangnUsedmarketBoardChatRoomDAO;
import kr.ezen.daangn.dao.DaangnUsedmarketBoardDAO;
import kr.ezen.daangn.dao.DaangnUsedmarketBoardFileDAO;
import kr.ezen.daangn.dao.DaangnUsedmarketBoardLikeDAO;
import kr.ezen.daangn.dao.DaangnUsedmarketBoardReserveDAO;
import kr.ezen.daangn.vo.DaangnUsedmarketBoardFileVO;
import kr.ezen.daangn.vo.DaangnUsedmarketBoardVO;
import kr.ezen.daangn.vo.DaangnUsedmarketChatMessageVO;
import kr.ezen.daangn.vo.DaangnUsedmarketChatRoomVO;
import kr.ezen.daangn.vo.ScrollVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(value = "daangnUsedmarketService")
public class DaangnUsedmarketServiceImpl implements DaangnUsedmarketService{
	
	@Autowired
	private DaangnUsedmarketBoardDAO boardDAO;
	@Autowired
	private DaangnUsedmarketBoardFileDAO boardFileDAO;
	@Autowired
	private DaangnUsedmarketBoardLikeDAO boardLikeDAO;
	@Autowired
	private DaangnUsedmarketBoardReserveDAO reserveDAO;
	@Autowired
	private DaangnUsedmarketBoardChatRoomDAO chatRoomDAO;
	@Autowired
	private DaangnUsedmarketBoardChatMessageDAO chatMessageDAO;
	
	/**
	 * 중고거래 게시글 목록 얻기 (lastItemIdx, sizeOfPage, categoryRef, region, gu, dong, search, userRef)
	 * @param sv
	 * @return
	 */
	@Override
	public List<DaangnUsedmarketBoardVO> getUsedmarketBoards(ScrollVO sv) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 중고거래 게시글 가장 큰 idx 얻기
	 * @return
	 */
	@Override
	public int getBoardLastIdx() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 중고거래 게시글 한개 얻기
	 * @param idx
	 * @return
	 */
	@Override
	public DaangnUsedmarketBoardVO selectByIdx(int idx) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 중고거래 게시글 쓰기
	 * @param testVO
	 * @return
	 */
	@Override
	public int insertUsedmarketBoard(DaangnUsedmarketBoardVO boardVO) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 중고거래 게시글 수정하기
	 * @param testVO
	 * @return
	 */
	@Override
	public int updateUsedmarketBoard(DaangnUsedmarketBoardVO boardVO) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 중고거래 게시글 삭제하기
	 * @param idx
	 * @return
	 */
	@Override
	public int deleteUsedmarketBoard(int idx) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 중고거래 게시글 조회수 증가
	 * @param idx
	 * @return
	 */
	@Override
	public int incrementReadCount(int idx) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 게시글 상태 변경하기
	 * @param boardRef
	 * @param statusRef
	 * @return
	 */
	@Override
	public int updateStatus(int boardRef, int statusRef) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 유저가 쓴 글 주기 (lastItemIdx, sizeOfPage, userRef, statusRef)
	 * @param sv
	 * @return
	 */
	@Override
	public List<DaangnUsedmarketBoardVO> getUsedmarketBoardsByUserRef(ScrollVO sv) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 유저의 판매상품의 상태에 따른 갯수 얻기 (userRef, statusRef)
	 * @param sv
	 * @return
	 */
	@Override
	public int getBoardCountByUserIdxAndStatusRef(ScrollVO sv) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 게시글에 해당하는 유저의 다른 게시물 얻기 (userRef, boardRef)
	 * @param sv
	 * @return
	 */
	@Override
	public List<DaangnUsedmarketBoardVO> selectListByUserIdxAndNotBoardIdx(ScrollVO sv) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 중고거래 게시물 사진 저장
	 * @param usedmarketBoardFileVO
	 * @return
	 */
	@Override
	public int insertUsedmarketBoardFile(DaangnUsedmarketBoardFileVO usedmarketBoardFileVO) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 중고거래 게시물 사진 삭제(게시글에 해당하는)
	 * @param boardRef
	 * @return
	 */
	@Override
	public int deleteUsedmarketBoardFile(int boardRef) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//=========================================================================
	// 좋아요 관련
	//=========================================================================
	/**
	 * 중고거래 게시글 찜하기
	 * @param boardRef
	 * @param userRef
	 * @return
	 */
	@Override
	public int incrementLikeCount(int boardRef, int userRef) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 중고거래 게시글 찜 취소하기
	 * @param boardRef
	 * @param userRef
	 * @return
	 */
	@Override
	public int decrementLikeCount(int boardRef, int userRef) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 중고거래 게시물 좋아요 확인
	 * @param userRef
	 * @param boardRef
	 * @return
	 */
	@Override
	public int isUsedmarketBoardLike(int userRef, int boardRef) {
		// TODO Auto-generated method stub
		return 0;
	}
	
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
	@Override
	public int createChatRoom(int userRef, int boardRef) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 1. 채팅방 목록 보기 (LastUpdateDate 기준)
	 * 2. 안읽은 채팅 메시지 수 가져오기(userRef, chatRoomRef) 채팅방에 해당
	 * 3. 마지막 채팅, 어떤 board, 어느 유저랑
	 * @param userRef
	 * @return
	 */
	@Override
	public List<DaangnUsedmarketChatRoomVO> getChatRooms(int userRef) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
     * 채팅방 삭제하기
     * @param chatRoomRef 채팅방 인덱스
     * @return 삭제된 채팅방 수
     */
	@Override
	public int deleteChatRoom(int chatRoomRef, Integer deleted1, Integer deleted2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
     * 1. 채팅메시지 저장
     * 2. 채팅방 LastUpdateDate를 업데이트
     * @param chatMessageVO
     */
	@Override
	public void insertMessage(DaangnUsedmarketChatMessageVO chatMessageVO) {
		// TODO Auto-generated method stub
		
	}

	// 날라온 idx로 readed--
	@Override
	public void updateReadCount(int idx) {
		// TODO Auto-generated method stub
		
	}
	
	// 안읽은 채팅 메시지 수 가져오기(userRef) 모든 채팅방의 총합
	@Override
	public int getUnReadCountByUserRef(int userRef) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
     * 채팅메시지 가져오기
     * @param sizeOfPage 한 페이지에 표시할 채팅메시지 수
     * @param lastItemIdx 마지막 채팅메시지 인덱스
     * @param chatRoomRef 채팅방 인덱스
     * @param deleted1, deleted1 누가 호출하는지
     * @return 채팅메시지 목록
     */
	@Override
	public List<DaangnUsedmarketChatMessageVO> getPagedChatMessages(int lastItemIdx, int sizeOfPage, int chatRoomRef,
			Integer deleted1, Integer deleted2) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
     * 채팅메시지 가장 큰 idx
     * @return
     */
	@Override
	public int getChatMessageLastIdx() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
