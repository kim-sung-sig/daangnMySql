package kr.ezen.daangn.global.security.jwt.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ezen.daangn.global.security.jwt.entity.RefreshTokenVO;

@Mapper
public interface RefreshTokenDAO {

    /**
     * idx로 토큰 조회
     * @param idx
     * @return
     */
    RefreshTokenVO findByIdx(int idx);

    /**
     * 유저 번호로 토큰 조회
     * @param userRef
     * @return
     */
    RefreshTokenVO findByUserRef(int userRef);

    /**
     * 리프래쉬 토큰 으로 조회
     * @param refreshToken
     * @return
     */
    RefreshTokenVO findByRefreshToken(String refreshToken);
    
    /**
     * 리프래쉬 토큰 저장
     * @param refreshTokenVO
     */
    void insertRefreshToken(RefreshTokenVO refreshTokenVO);

    /**
     * 리프래쉬 토큰 업데이트
     * @param userRef
     * @param refreshToken
     */
    void updateRefreshToken(@Param("userRef") int userRef, @Param("refreshToken") String refreshToken);

    /**
     * 리프래쉬 토큰 삭제
     * userRef or refreshToken 에 해당하는 정보 삭제
     * @param userRef
     * @param refreshToken
     */
    void deleteRefreshToken(@Param("userRef") int userRef, @Param("refreshToken") String refreshToken);

}
