package kr.ezen.daangn.global.security.jwt.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 리프래쉬 토큰을 저장하기 위한 객체
 */
@Getter
@AllArgsConstructor
public class RefreshTokenEntity {

    private Integer idx;

    private Integer userRef;

    private String refreshToken;

    public RefreshTokenEntity(Integer userRef, String refreshToken){
        this.userRef = userRef;
        this.refreshToken = refreshToken;
    }

    public void update(String newRefreshToken){
        this.refreshToken = newRefreshToken;
    }
    
}
