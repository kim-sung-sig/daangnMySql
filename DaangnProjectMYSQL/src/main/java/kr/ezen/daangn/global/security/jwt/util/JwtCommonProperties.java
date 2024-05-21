package kr.ezen.daangn.global.security.jwt.util;

public class JwtCommonProperties {

    /**
     * AccessToken 이름 지정
     */
    public final static String HEADER_AUTHORIZATION = "Authorization";

    /**
     * RefreshToken 이름 지정
     */
    public final static String REFRESH_TOKEN = "refreshToken";

    /**
     * 토큰 시작 글자 지정
     */
    public final static String TOKEN_PREFIX = "Bearer ";

    /**
     * AccessToken 유지 시간
     */
    public final static Long ACCESS_TOKEN_EXPIRE_TIME = 1L * 1000 * 60 * 15; // 15분

    /**
     * RefreshToken 유지 시간
     */
    public final static Long REFRESH_TOKEN_EXPIRE_TIME = 1L * 1000 * 60 * 60 * 24 * 15; // 15d
    
}
