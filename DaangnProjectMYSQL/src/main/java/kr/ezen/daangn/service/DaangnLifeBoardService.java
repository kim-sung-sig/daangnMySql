package kr.ezen.daangn.service;

import java.util.List;

import kr.ezen.daangn.vo.DaangnLifeBoardFileVO;
import kr.ezen.daangn.vo.DaangnLifeBoardVO;
import kr.ezen.daangn.vo.DaangnLifeCommentVO;
import kr.ezen.daangn.vo.ScrollVO;

public interface DaangnLifeBoardService {
    
    /**
     * 동네생활 게시글 목록 얻기 (lastItemIdx, sizeOfPage, categoryRef, region, gu, dong, search, userRef)
     * @param sv
     * @return
     */
    List<DaangnLifeBoardVO> selectPagedLifeBoards(ScrollVO sv);
    
    /**
     * 동네생활 게시글 가장 큰 idx 얻기
     * @return
     */
    int getBoardLastIdx();
    
    /**
     * 동네생활 게시글 한개 얻기
     * @param idx
     * @return
     */
    DaangnLifeBoardVO selectByIdx(int idx);
    
    /**
     * 동네생활 게시글 쓰기
     * @param lifeBoardVO
     * @return
     */
    int insertLifeBoard(DaangnLifeBoardVO lifeBoardVO);
    
    /**
     * 동네생활 게시글 수정하기
     * @param lifeBoardVO
     * @return
     */
    int updateLifeBoard(DaangnLifeBoardVO lifeBoardVO);
    
    /**
     * 동네생활 게시글 삭제하기
     * @param idx
     * @return
     */
    int deleteLifeBoard(int idx);
    
    /**
     * 동네생활 게시글 조회수 증가
     * @param idx
     * @return
     */
    int incrementReadCount(int idx);
    
    /**
     * 동네생활 게시글 좋아요
     * @param idx
     * @return
     */
    int incrementLikeCount(int boardRef, int userRef);
    
    /**
     * 동네생활 게시글 좋아요취소
     * @param idx
     * @return
     */
    int decrementLikeCount(int boardRef, int userRef);
    
    /**
     * 동네생활 유저가 쓴 게시글 수 얻기
     * @param userRef
     * @return
     */
    int getBoardCountByUserRef(int userRef);
    
    // =================================================================================================================================
    // 댓글 관련
    // =================================================================================================================================
    
    /**
     * 동네생활 게시글의 댓글 목록 얻기 (lastItemIdx, sizeOfPage, boardRef)
     * @param sv
     * @return
     */
    List<DaangnLifeCommentVO> selectPagedLifeBoardComments(ScrollVO sv);
    
    /**
     * 동네생활 댓글 한개 얻기 (댓글 수정용)
     * @param idx
     * @return
     */
    DaangnLifeCommentVO selectCommentByIdx(int idx);
    
    /**
     * 동네생활 게시글의 댓글의 대댓글 얻기 (한번에 조회)
     * @param commentRef
     * @return
     */
    List<DaangnLifeCommentVO> selectLifeBoardChildComments(int commentRef);
    
    /**
     * 동네생활 댓글 가장 큰 idx 얻기
     * @return
     */
    int getCommentLastIdx();
    
    /**
     * 동네생활 게시글 댓글 쓰기 (boardRef, comment, parentComIdx[null])
     * @param lifeCommentVO
     * @return
     */
    int insertLifeBoardComment(DaangnLifeCommentVO lifeCommentVO);
    
    /**
     * 동네생활 게시글 댓글 수정 (idx, comment)
     * @param lifeCommentVO
     * @return
     */
    int updateLifeBoardComment(DaangnLifeCommentVO lifeCommentVO);
    
    /**
     * 동네생활 게시물 댓글 삭제
     * @param commentRef
     * @param boardRef
     * @return
     */
    int deleteLifeBoardComment(int commentRef, int boardRef, Integer parentComIdx);
    
    /**
     *  동네생활 게시물 댓글 좋아요
     * @param commentRef
     * @param userRef
     * @return
     */
    int incrementCommentLikeCount(int userRef, int commentRef);
    
    /**
     * 동네생활 게시물 댓글 좋아요 취소
     * @param commentRef
     * @param userRef
     * @return
     */
    int decrementCommentLikeCount(int userRef, int commentRef);
    
    /**
     * 동네생활 유저가 쓴 댓글 수 얻기
     * @param userRef
     * @return
     */
    int getCommentCountByUserRef(int userRef);
    
    /**
     * 동네생활 댓글쓴 게시글 얻기
     * @param sv (lastItemIdx, sizeOfPage, userRef)
     * @return
     */
    List<DaangnLifeBoardVO> getCommentedBoardByUserRef(ScrollVO sv);

    /**
     * 동네생활 좋아요 가장 큰 idx 얻기
     * @param userRef
     * @return
     */
    int getLikeLastItemIdx();
    
    /**
     * 동네생활 유저가 누른 좋아요 수 얻기
     * @param userRef
     * @return
     */
    int getLikeCountByUserRef(int userRef);
    
    /**
     * 동네생활 유저가 좋아요한 게시글 얻기
     * @param lastItemIdx
     * @param sizeOfPage
     * @param userRef
     * @return
     */
    List<DaangnLifeBoardVO> getLikedBoardsByUserRef(ScrollVO sv);

    // =================================================================================================================================
    // 좋아요 했는지 안했는지 확인
    // =================================================================================================================================
    
    /**
     * 동네생활 게시물 좋아요 확인
     * @param userRef
     * @param boardRef
     * @return
     */
    int isLifeBoardLike(int userRef, int boardRef);
    
    /**
     * 동네생활 게시물 댓글 좋아요 확인
     * @param userRef
     * @param boardRef
     * @return
     */
    int isLifeCommentLike(int userRef, int commentRef);
    
    /**
     * 동네생활 게시물 사진 저장
     * @param lifeBoardFileVO
     * @return
     */
    int insertLifeBoardFile(DaangnLifeBoardFileVO lifeBoardFileVO);
    
    /**
     * 동네생활 게시물 사진 삭제(게시글에 해당하는)
     * @param boardRef
     * @return
     */
    int deleteLifeBoardFile(int boardRef);
    
    /**
     * 검색상태에 따른 갯수 얻기(categoryRef, region, gu, dong, search)
     * @param categoryRef
     * @param region
     * @param gu
     * @param dong
     * @param search
     * @return
     */
    int getBoardCountBy(Integer categoryRef, String region, String gu, String dong, String search); 
}
