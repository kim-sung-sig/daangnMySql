package kr.ezen.daangn.global.security.jwt.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import kr.ezen.daangn.global.security.jwt.dao.RefreshTokenDAO;
import kr.ezen.daangn.global.security.jwt.dto.JwtDTO;
import kr.ezen.daangn.global.security.jwt.entity.RefreshTokenEntity;
import kr.ezen.daangn.vo.DaangnMemberVO;

@Component
public class JwtUtil {

    private final SecretKey secretKey;

    private final RefreshTokenDAO refreshTokenDAO;
    
    public JwtUtil(@Value("${custom.jwt.secretKey}") String originSecretKey, RefreshTokenDAO refreshTokenDAO){
        this.secretKey = new SecretKeySpec(originSecretKey.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
        this.refreshTokenDAO = refreshTokenDAO;
    }

    public JwtDTO createJWT(DaangnMemberVO user){

        String accessToken = Jwts.builder()
                .claim("category", "access")
                .claim("idx", user.getIdx())
                .claim("username", user.getUsername())
                .claim("role", user.getRole())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + JwtCommonProperties.ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(secretKey)
                .compact();

        String refreshToken = Jwts.builder()
                .claim("category", "refresh")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + JwtCommonProperties.REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(secretKey)
                .compact();

        RefreshTokenEntity refreshTokenVO = new RefreshTokenEntity(user.getIdx(), refreshToken);
        refreshTokenDAO.insertRefreshToken(refreshTokenVO); // 토큰을 발급하며 토큰 저장
        return new JwtDTO(accessToken, refreshToken);
    }

    public boolean validateToken(String token) {
        try{
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * 만료인지 확인
     * @param token
     * @return
     */
    public Boolean isExpired(String token) {
        try{
            return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
        } catch (JwtException e){
            return true; // 예외가 발생하면 만료된것임
        }
    }

    public Integer getIdx(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("idx", Integer.class);
    }

    public String getUsername(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("username", String.class);
    }

    public String getRole(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    public String getCategory(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("category", String.class);
    }

}
