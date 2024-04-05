package kr.ezen.daangn.vo;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class TestVO {
	private int idx;
	private int userRef;				// user의 idx 왜래키
	private int categoryRef;			// category의 idx 왜래키
	private int statusRef;				// status의 idx 왜래키
	
	private String title;				// 제목
	private String content;				// 내용
	
	private int price;					// 가격
	
	private double latitude;			// 위도 kakaoMap에 표시하기 위한 값들 
	private double longitude;			// 경도
	
	private String loc1;				// 서울시
	private String loc2;				// 강서구
	private String loc3;				// 화곡1동
	
	private String location;			// 상세위치
	
	private int readCount;				// 조회수
	private LocalDateTime regDate;		// 게시일
		
	//==================================================
	// db세팅끝
	// 공통사용
	private String categoryName;					// 카테고리 이름 1~12 중고마켓, 13 생활꿀팁, 14 QnA
	private String statusName;						// 상태 이름	1 판매중, 2 예약중, 3 판매완료
	private int countLike;							// 좋아요 수
	private int chatRoomCount;						// 체팅 수 읽기?
	
	// 리스트 보기때 사용
	private String boardFile;						// 리스트 뿌릴때
	
	// 하나 보기때 사용
	private String nickName;						// 닉네임
	private String userProfile;						// 프로프로필
	private Float userVal;							// 유저평
	private List<DaangnFileVO> boardFileList;		// 사진등록
}
