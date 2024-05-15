package kr.ezen.daangn.domain.lifeboard.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DaangnLifeBoardEntity {

    private int idx;

    private int userRef;

    private int categoryRef;

    private String title;

    private String content;

    private String loc1;

    private String loc2;

    private String loc3;

    private int readCount;

    private LocalDateTime createdDate;

    private LocalDateTime updateDate;

    private String ip;

}
