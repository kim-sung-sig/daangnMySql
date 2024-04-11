package kr.ezen.daangn.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DaangnUsedmarketChatMessageVO {
	private int idx;
	private int chatRoomRef;
	private int typeRef;
	private int sender;
	private String content;
	private LocalDateTime createDate;
	
	private int readed = 2;
	private int deleted1;
	private int deleted2;
	// end - DB Setting
	
	private String nickName;
	private String userProfile;
}
