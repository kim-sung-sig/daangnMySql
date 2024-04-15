package kr.ezen.daangn.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ezen.daangn.vo.DaangnLifeBoardVO;

@Mapper
public interface DaangnLifeBoardDAO {
	/**
	 * 동네생활 게시글 페이징 조회
	 * @param lastItemIdx
	 * @param sizeOfPage
	 * @param categoryRef
	 * @param search
	 * @param userRef
	 * @return
	 */
	List<DaangnLifeBoardVO> selectPagedLifeBoards(
	        @Param("lastItemIdx") int lastItemIdx,
	        @Param("sizeOfPage") int sizeOfPage,
	        @Param("categoryRef") Integer categoryRef,
	        @Param("region") String region,
	        @Param("gu") String gu,
	        @Param("dong") String dong,
	        @Param("search") String search,
	        @Param("userRef") Integer userRef
	    ) throws SQLException;
	
	/**
	 * 최대 idx 얻기
	 * @return
	 */
    int getLastIdx() throws SQLException;
    
    /**
     * 동네생활 게시글 한개 얻기
     * @param idx
     * @return
     * @throws SQLException
     */
    DaangnLifeBoardVO selectByIdx(int idx) throws SQLException;
    
    /**
     * 동네생활 게시글 저장
     * @param lifeBoardVO
     */
    void insertLifeBoard(DaangnLifeBoardVO lifeBoardVO) throws SQLException;
    
    /**
     * 동네생활 게시글 수정
     * @param lifeBoardVO
     */
    void updateLifeBoard(DaangnLifeBoardVO lifeBoardVO) throws SQLException;
    
    /**
     * 동네생활 게시글 삭제
     * @param idx
     */
    void deleteLifeBoard(int idx) throws SQLException;
    
    /**
     * 동네생활 게시글 조회수 증가
     * @param idx
     */
    void incrementReadCount(int idx) throws SQLException;
    
    /**
     * 동네생활 게시글 좋아요 수 증가
     * @param idx
     */
    void incrementLikeCount(int idx) throws SQLException;
    
    /**
     * 동네생활 게시글 좋아요 수 감소
     * @param idx
     */
    void decrementLikeCount(int idx) throws SQLException;
    
    /**
     * 동네생활 게시글 댓글 수 증가
     * @param idx
     */
    void incrementCommentCount(int idx) throws SQLException;
    
    /**
     * 동네생활 게시글 댓글 수 감소
     * @param idx
     * @param commentCount
     * @throws SQLException
     */
    void decrementCommentCount(@Param("idx") int idx, @Param("commentCount") int commentCount) throws SQLException;
    
    /**
     * 동네생활 유저가 쓴 게시글 수 얻기
     * @param userRef
     * @return
     */
    int getTotalCountByUserRef(@Param("userRef") int userRef) throws SQLException;
    
    /**
     * 검색상태에 따른 갯수 얻기(categoryRef, region, gu, dong, search)
     * @param categoryRef
     * @param region
     * @param gu
     * @param dong
     * @param search
     * @return
     */
    int getTotalCountBy(
    		@Param("categoryRef") Integer categoryRef,
    		@Param("region") String region,
    		@Param("gu") String gu,
    		@Param("dong") String dong,
    		@Param("search") String search
    	) throws SQLException;
}

