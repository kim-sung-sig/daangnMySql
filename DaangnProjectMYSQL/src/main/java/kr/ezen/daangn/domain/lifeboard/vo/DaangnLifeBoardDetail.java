package kr.ezen.daangn.domain.lifeboard.vo;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class DaangnLifeBoardDetail {
    
    private int idx;

    private int userRef;
	private String nickName;
	private String userProfile;
	private Float userVal = 3f;
    
    private int categoryRef;
    private String categoryName;

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

    private List<String> fileList;

    private boolean isLike;

}
