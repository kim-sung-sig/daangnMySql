package kr.ezen.daangn.vo;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class DaangnUsedmarketBoardVO {
	private int idx;
	private int userRef;				// user의 idx 왜래키
	private int categoryRef;			// category의 idx 왜래키
	private int statusRef;				// status의 idx 왜래키
	
	private String thumbnail;			// 맨 첫 사진
	private String title;				// 제목
	private String content;				// 내용
	
	private int price;					// 가격
	
	// kakaoMap에 표시하기 위한 값들
	private double latitude;			// 위도  
	private double longitude;			// 경도
	
	private String loc1;				// ex) 서울시
	private String loc2;				// ex) 강서구
	private String loc3;				// ex) 화곡1동
	
	private String location;			// 상세위치
	
	private int readCount;				// 조회수
	private int likeCount;				// 좋아요 수
	private int chatRoomCount;			// 채팅 수
	private LocalDateTime createDate;	// 게시일
	private LocalDateTime updateDate;	// 수정일 > 게시글을 수정했을때만 업데이트 되도록하기!
	
	private String ip;
	// end - DB Setting
	
	private String categoryName;									// 카테고리 이름
	private String statusName;										// 상태 이름	1 판매중, 2 예약중, 3 판매완료
	private String nickName;										// 닉네임
	private String userProfile;										// 유저프로필
	private Float userVal;											// 유저평
	private List<DaangnUsedmarketBoardFileVO> boardFileList;		// 파일 리스트

	// 좋아요 했는지 안했는지 확인
	private int isLike;
	
	private Integer likeIdx;
	private Integer reserveIdx;
}
