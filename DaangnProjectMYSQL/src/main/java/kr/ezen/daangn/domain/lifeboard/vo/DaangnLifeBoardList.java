package kr.ezen.daangn.domain.lifeboard.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DaangnLifeBoardList {

    private int idx;
	
	private int userRef;
	
	private int categoryRef;
	private String categoryName;
	
    private String thumbnail;

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
    
}
