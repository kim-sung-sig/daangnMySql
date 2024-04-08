package kr.ezen.daangn.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DaangnUsedmarketChatRoomVO {
	private int roomIdx;
	private int userRef;
	private int boardRef;
	private int boardUserRef;
	private LocalDateTime lastUpdateDate;
	
	private Integer deleted1; // userRef가 삭제했는지
	private Integer deleted2; // boardUserRef가 삭제했는지
	private Integer isActivate; // 활성중 / 비활성중
	// end - DB setting
	
	private DaangnUsedmarketBoardVO board;
	
	private String nickName;
	private String userProfile;
	
	private int unreadCount;
	private DaangnUsedmarketChatMessageVO lastMessage;
}
