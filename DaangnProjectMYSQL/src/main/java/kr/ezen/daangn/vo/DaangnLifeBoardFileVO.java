package kr.ezen.daangn.vo;

import lombok.Data;

@Data
public class DaangnLifeBoardFileVO {
    private int idx; 
    private int boardRef;
    private String saveFileName; // 저장된 파일명
}
