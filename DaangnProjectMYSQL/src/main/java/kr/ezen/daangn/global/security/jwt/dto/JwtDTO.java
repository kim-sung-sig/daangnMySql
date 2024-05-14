package kr.ezen.daangn.global.security.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtDTO {

    String accessToken;

    String refreshToken;
    
}
