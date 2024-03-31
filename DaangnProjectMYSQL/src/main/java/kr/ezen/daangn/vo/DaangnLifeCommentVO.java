package kr.ezen.daangn.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DaangnLifeCommentVO {
    private int idx;
    
    private int userRef;
    
    private int boardRef;
    
    private Integer parentComIdx; // 부모 댓글 인덱스 (null 가능)
    
    private String comment;
    
    private int likeCount;
    
    private int childCommentCount;
    
    private Integer isOwner;
    
    private LocalDateTime createDate;
    
    private LocalDateTime updateDate;
    
    private String ip;
    
    // 추가기입
    private String nickName;
	private String userProfile;
	private int isLike;
}
