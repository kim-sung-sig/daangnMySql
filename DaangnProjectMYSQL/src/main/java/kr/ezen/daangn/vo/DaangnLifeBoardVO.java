package kr.ezen.daangn.vo;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class DaangnLifeBoardVO {
	private int idx;
	
	private int userRef;
	
	private int categoryRef;
	
	private String title;
	
	private String content;
	
	private String loc1;
	
	private String loc2;
	
	private String loc3;
	
	private int readCount;
	
	private int likeCount;
	
	private int commentCount;
	
	private LocalDateTime createDate;
	
	private LocalDateTime updateDate;
	
	private String ip;
	// db설계
	
	// 추가 기입
	private String categoryName;
	private String nickName;
	private String userProfile;
	private Float userVal = 3f;
	private List<DaangnLifeBoardFileVO> fileList;
	private int isLike;
}
