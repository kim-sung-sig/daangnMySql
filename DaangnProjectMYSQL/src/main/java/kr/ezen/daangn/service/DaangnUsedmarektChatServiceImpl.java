package kr.ezen.daangn.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ezen.daangn.dao.DaangnUsedmarketBoardChatMessageDAO;
import kr.ezen.daangn.dao.DaangnUsedmarketBoardChatRoomDAO;
import kr.ezen.daangn.dao.DaangnUsedmarketBoardDAO;
import kr.ezen.daangn.vo.DaangnMemberVO;
import kr.ezen.daangn.vo.DaangnUsedmarketChatMessageVO;
import kr.ezen.daangn.vo.DaangnUsedmarketChatRoomVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(value = "daangnUsedmarektChatService")
public class DaangnUsedmarektChatServiceImpl implements DaangnUsedmarektChatService{
	
	@Autowired
	private DaangnUsedmarketBoardChatRoomDAO chatRoomDAO;
	
	@Autowired
	private DaangnUsedmarketBoardDAO boardDAO;
	
	@Autowired
	private DaangnUsedmarketBoardChatMessageDAO chatMessageDAO;
	
	@Autowired
	private DaangnMemberService memberService;
	
	@Override
	public int createChatRoom(int userRef, int boardRef, int boardUserRef) {
		int result = 0;
		try {
			result = chatRoomDAO.checkUniqueChatRoom(userRef, boardRef);
			if(result == 0) { // 채팅방이 없었으면 만들어서 리턴
				var chatRoom = new DaangnUsedmarketChatRoomVO();
				chatRoom.setUserRef(userRef);
				chatRoom.setBoardRef(boardRef);
				chatRoom.setBoardUserRef(boardUserRef);
				chatRoomDAO.createChatRoom(chatRoom);
				boardDAO.incrementChatRoomCount(boardRef);
				result = chatRoom.getRoomIdx();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<DaangnUsedmarketChatRoomVO> getChatRoomsByUserRef(int userRef) {
		log.info("getChatRoomsByUserRef 실행 userRef => {}", userRef);
		List<DaangnUsedmarketChatRoomVO> list = null;
		try {
			list = chatRoomDAO.getChatRoomsByUserRef(userRef);
			for(var cr: list) {
				cr.setBoard(boardDAO.selectByIdx(cr.getBoardRef())); // board
				cr.setLastMessage(chatMessageDAO.selectLastMessageByChatRoomRef(cr.getRoomIdx())); // 마지막 메시지
				if(userRef == cr.getBoardUserRef()) {
					var member = memberService.selectByIdx(cr.getUserRef());
					cr.setNickName(member.getNickName());
					if(member.getUserFile() != null) {
						cr.setUserProfile(member.getUserFile().getSaveFileName());
					}
				} else {
					var member = memberService.selectByIdx(cr.getBoardUserRef());
					cr.setNickName(member.getNickName());
					if(member.getUserFile() != null) {
						cr.setUserProfile(member.getUserFile().getSaveFileName());
					}
				}
				cr.setUnreadCount(chatMessageDAO.unreadCount(cr.getRoomIdx(), userRef));
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("getChatRoomsByUserRef 리턴 {}개 {}", list.size(), list);
		return list;
	}

	@Override
	public int getUnreadCountByUserRef(int userRef) {
		log.info("");
		int result = 0;
		try {
			List<DaangnUsedmarketChatRoomVO> list = chatRoomDAO.getChatRoomsByUserRef(userRef);
			for(var cr: list) {
				result += chatMessageDAO.unreadCount(cr.getRoomIdx(), userRef);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("getUnreadCountByUserRef 리턴 {}개", result);
		return result;
	}

	@Override
	public int insertMessage(DaangnUsedmarketChatMessageVO messageVO) {
		int result = 0;
		try {
			chatMessageDAO.insertChat(messageVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deactiveteChatRoom(int chatRoomRef, Integer deleted1, Integer deleted2) {
		return 0;
	}

	@Override
	public void updateReadCount(int idx) {
		try {
			chatMessageDAO.updateChat(idx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateReadCountAll(int chatRoomRef, int sender) {
		try {
			chatMessageDAO.updateAllChat(chatRoomRef, sender);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getChatMessageLastIdx() {
		int result = 0;
		try {
			result = chatMessageDAO.getLastIdx();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<DaangnUsedmarketChatMessageVO> getMessagesByChatRoomRef(
			int chatRoomRef, int lastItemIdx,int sizeOfPage,
			Integer deleted1, Integer deleted2
		) {
		List<DaangnUsedmarketChatMessageVO> list = null;
		try {
			list = chatMessageDAO.selectPagedChatMessageByChatRoomRef(lastItemIdx, sizeOfPage, chatRoomRef, deleted1, deleted2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public DaangnUsedmarketChatRoomVO selectChatRoomByIdx(int idx) {
		DaangnUsedmarketChatRoomVO chatRoomVO = null;
		try {
			chatRoomVO = chatRoomDAO.selectChatRoomByIdx(idx);
			if(chatRoomVO != null) {
				chatRoomVO.setBoard(boardDAO.selectByIdx(chatRoomVO.getBoardRef()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chatRoomVO;
	}
	
	@Override
	public List<DaangnMemberVO> getChatRoomUserByBoardRef(int boardRef) {
		List<DaangnMemberVO> userList = null;
		try {
			var list = chatRoomDAO.getChatRoomByboardRef(boardRef);
			if(list != null && list.size() > 0) {
				userList = new ArrayList<DaangnMemberVO>();
				for(var cr : list) {
					var user = memberService.selectByIdx(cr.getUserRef());
					if(user != null) {
						userList.add(user);					
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
}
