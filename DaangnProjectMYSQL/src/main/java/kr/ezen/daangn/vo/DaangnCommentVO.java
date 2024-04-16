package kr.ezen.daangn.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DaangnCommentVO {
	private int idx;					// 키필드
	private int userRef;				// 어떤 유저에 대하여
	private int writerRef;				// 어떤 유저가
	private int score;					// 총점?				// 유저랑 연결! 1 , 3, 5;
	private String content;				// 댓글
	private LocalDateTime createDate;	// 댓글쓴 날짜
	private String ip;					// 아이피
	// DB 설계 끝
	
	
}
