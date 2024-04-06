package kr.ezen.daangn.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DaangnUsedmarketReserveVO {
	private int idx;
	private int boardRef;
	private int userRef;
	private LocalDateTime reserveDate; 
	private int interaction;
}
