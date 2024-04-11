package kr.ezen.daangn.service;

import java.util.List;

import kr.ezen.daangn.vo.DaangnMemberVO;
import kr.ezen.daangn.vo.DaangnUsedmarketChatMessageVO;
import kr.ezen.daangn.vo.DaangnUsedmarketChatRoomVO;

public interface DaangnUsedmarektChatService {
	
	// 1. 채팅방 개설 혹은 조회(입장)
	int createChatRoom(int userRef, int boardRef, int boardUserRef);
	
	// 2. 채팅방 목록 보기 (user, lastMessage, board, unreadCount)
	List<DaangnUsedmarketChatRoomVO> getChatRoomsByUserRef(int userRef);
	
	// 3. 총 안읽은 메시지 리턴
	int getUnreadCountByUserRef(int userRef);
	
	// 4. 저장(메시지 저장)
	int insertMessage(DaangnUsedmarketChatMessageVO messageVO);
	
	// 5. 삭제(채팅방 삭제)
	int deactiveteChatRoom(int chatRoomRef, Integer deleted1, Integer deleted2);
	
	// 6. 읽음 처리
	void updateReadCount(int idx);
	
	// 7. 일괄 읽음 처리
	void updateReadCountAll(int chatRoomRef, int sender);
	
	// 8. 최대 idx 얻기
	int getChatMessageLastIdx();
	
	// 9. 메시지 목록 얻기
	List<DaangnUsedmarketChatMessageVO> getMessagesByChatRoomRef(int chatRoomRef, int lastItemIdx, int sizeOfPage, Integer deleted1, Integer deleted2);
	
	// 10. 채팅방 한개얻기(이때 board충전)
	DaangnUsedmarketChatRoomVO selectChatRoomByIdx(int idx);
	
	// 11. 게시글에 해당하는 채팅방 유저들 목록주기
	List<DaangnMemberVO> getChatRoomUserByBoardRef(int boardRef);
}
