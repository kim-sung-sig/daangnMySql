package kr.ezen.daangn.vo;

import lombok.Data;

@Data
public class ScrollVO {
	private int lastItemIdx;
	private int sizeOfPage;
	private Integer categoryRef;
	private String search;
	private String region;
	private String gu;
	private String dong;
	private Integer chatRoomIdx;
	private Integer userRef;
	private Integer statusRef;
	private Integer boardRef;
}
