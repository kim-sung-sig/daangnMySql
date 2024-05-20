package kr.ezen.daangn.domain.chat.vo;

import java.util.Date;
import java.util.List;

import kr.ezen.daangn.vo.DaangnMainBoardVO;
import kr.ezen.daangn.vo.DaangnMemberVO;
import lombok.Data;

@Data
public class ChatRoomVO2 {

	private int roomIdx;
	private int userIdx;
	private int boardIdx;
	private int boardUserIdx;
	private Date lastUpdateDate;
	// db설계
	
	private DaangnMainBoardVO board;
	private DaangnMemberVO member;
	private List<ChatMessageVO2> messageList;
	private int unreadCount;
	
}
