package kr.ezen.daangn.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ezen.daangn.dao.DaangnLifeBoardCommentDAO;
import kr.ezen.daangn.dao.DaangnLifeBoardCommentLikeDAO;
import kr.ezen.daangn.dao.DaangnLifeBoardDAO;
import kr.ezen.daangn.dao.DaangnLifeBoardFileDAO;
import kr.ezen.daangn.dao.DaangnLifeBoardLikeDAO;
import kr.ezen.daangn.vo.DaangnLifeBoardFileVO;
import kr.ezen.daangn.vo.DaangnLifeBoardVO;
import kr.ezen.daangn.vo.DaangnLifeCommentVO;
import kr.ezen.daangn.vo.ScrollVO;
import lombok.extern.slf4j.Slf4j;

@Service("daangnLifeBoardService")
@Slf4j
public class DaangnLifeBoardServiceImpl implements DaangnLifeBoardService {

    @Autowired
    private DaangnLifeBoardDAO lifeBoardDAO;
    @Autowired
    private DaangnLifeBoardLikeDAO lifeBoardLikeDAO;
    @Autowired
    private DaangnLifeBoardFileDAO lifeBoardFileDAO;
    
    @Autowired
    private DaangnLifeBoardCommentDAO lifeBoardCommentDAO;
    @Autowired
    private DaangnLifeBoardCommentLikeDAO lifeBoardCommentLikeDAO;
    
    /**
     * 동네생활 게시글 목록 얻기 (lastItemIdx, sizeOfPage, categoryRef, region, gu, dong, search, userRef)
     * @param sv
     * @return
     */
    @Override
    public List<DaangnLifeBoardVO> selectPagedLifeBoards(ScrollVO sv) {
    	log.info("selectPagedLifeBoards 실행 (lastItemIdx, sizeOfPage, categoryRef, region, gu, dong, search) => ({}, {}, {}, {}, {}, {}, {})",sv.getLastItemIdx(), sv.getSizeOfPage(), sv.getCategoryRef(), sv.getRegion(), sv.getGu(), sv.getDong(), sv.getSearch());
    	List<DaangnLifeBoardVO> list = null;
    	try {
    		list = lifeBoardDAO.selectPagedLifeBoards(sv.getLastItemIdx(), sv.getSizeOfPage(), sv.getCategoryRef(), sv.getRegion(), sv.getGu(), sv.getDong(), sv.getSearch(), sv.getUserRef());
    		for(DaangnLifeBoardVO boardVO : list) {
    			boardVO.setFileList(lifeBoardFileDAO.selectLifeBoardFileByBoardRef(boardVO.getIdx())); // 게시글 사진 넣어주기
    		}
    	} catch (SQLException e) {
            e.printStackTrace();
        }
    	log.info("selectPagedLifeBoards 리턴 {}개, {}", list.size(), list);
    	return list;
    }
    
    /**
     * 동네생활 게시글 가장 큰 idx 얻기
     * @return
     */
    @Override
    public int getBoardLastIdx() {
    	int result = 0;
        try {
        	result = lifeBoardDAO.getLastIdx();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 동네생활 게시글 한개 얻기
     * @param idx
     * @return
     */
    @Override
    public DaangnLifeBoardVO selectByIdx(int idx) {
    	log.info("selectByIdx 실행 idx => {}", idx);
    	DaangnLifeBoardVO boardVO = null;
		try {
			boardVO = lifeBoardDAO.selectByIdx(idx);
			if(boardVO != null) {
				boardVO.setFileList(lifeBoardFileDAO.selectLifeBoardFileByBoardRef(idx));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("selectByIdx 리턴 {}", boardVO);
    	return boardVO;
    }
    
    /**
     * 동네생활 게시글 쓰기
     * @param lifeBoardVO
     * @return
     */
    @Override
    public int insertLifeBoard(DaangnLifeBoardVO lifeBoardVO) {
    	log.info("insertLifeBoard 실행 {}", lifeBoardVO);
        int result = 0;
        try {
        	lifeBoardDAO.insertLifeBoard(lifeBoardVO);
        	result = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("insertLifeBoard 리턴 => {}", result);
        return result;
    }
    
    /**
     * 동네생활 게시글 수정하기
     * @param lifeBoardVO
     * @return
     */
    @Override
    public int updateLifeBoard(DaangnLifeBoardVO lifeBoardVO) {
    	log.info("updateLifeBoard 실행 {}", lifeBoardVO);
        int result = 0;
        try {
        	lifeBoardDAO.updateLifeBoard(lifeBoardVO);
        	result = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("updateLifeBoard 리턴 => {}", result);
        return result;
    }
    
    /**
     * 동네생활 게시글 삭제하기
     * @param idx
     * @return
     */
    @Override
    public int deleteLifeBoard(int idx) {
    	log.info("deleteLifeBoard 실행 {}", idx);
    	int result = 0;
        try {
        	lifeBoardDAO.deleteLifeBoard(idx);
        	result = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("deleteLifeBoard 리턴 => {}", result);
        return result;
    }
    
    /**
     * 동네생활 게시글 조회수 증가
     * @param idx
     * @return
     */
    @Override
    public int incrementReadCount(int idx) {
    	int result = 0;
        try {
        	lifeBoardDAO.incrementReadCount(idx);
        	result = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 동네생활 게시글 좋아요
     * @param idx
     * @return
     */
    @Override
    @Transactional
    public int incrementLikeCount(int boardRef, int userRef) {
    	int result = 0;
        try {
        	lifeBoardDAO.incrementLikeCount(boardRef); // 좋아요 수 증가
        	lifeBoardLikeDAO.insertLifeBoardLike(userRef, boardRef); // 좋아요 저장
        	result = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 동네생활 게시글 좋아요취소
     * @param idx
     * @return
     */
    @Override
    @Transactional
    public int decrementLikeCount(int boardRef, int userRef) {
    	int result = 0;
        try {
        	lifeBoardDAO.decrementLikeCount(boardRef); // 좋아요 수 감소
        	lifeBoardLikeDAO.deleteLifeBoardLike(userRef, boardRef); // 좋아요 삭제
        	result = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 동네생활 유저가 쓴 게시글 수 얻기
     * @param userRef
     * @return
     */
    @Override
    public int getBoardCountByUserRef(int userRef) {
    	int result = 0;
    	try {
			result = lifeBoardDAO.getTotalCountByUserRef(userRef);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return result;
    }
    
    // =================================================================================================================================
    // 댓글 관련
    // =================================================================================================================================
    
    /**
     * 동네생활 게시글의 댓글 목록 얻기 (lastItemIdx, sizeOfPage, boardRef)
     * @param sv
     * @return
     */
    @Override
    public List<DaangnLifeCommentVO> selectPagedLifeBoardComments(ScrollVO sv){
    	log.info("selectPagedLifeBoardComments 실행 (lastItemIdx, sizeOfPage, boardRef) => ({}, {}, {})", sv.getLastItemIdx(), sv.getSizeOfPage(), sv.getBoardRef());
    	List<DaangnLifeCommentVO> list = null;
    	try {
    		list = lifeBoardCommentDAO.selectPagedLifeBoardComments(sv.getLastItemIdx(), sv.getSizeOfPage(), sv.getBoardRef());
    	} catch (SQLException e) {
            e.printStackTrace();
        }
    	log.info("selectPagedLifeBoardComments 결과 {}개, {}", list.size(), list);
    	return list;
    }
    
    /**
     * 동네생활 댓글 한개 얻기 (댓글 수정용)
     * @param idx
     * @return
     */
    @Override
    public DaangnLifeCommentVO selectCommentByIdx(int idx) {
    	log.info("selectCommentByIdx 실행 idx => {}", idx);
    	DaangnLifeCommentVO lifeCommentVO = null;
    	try {
    		lifeCommentVO = lifeBoardCommentDAO.selectCommentByIdx(idx);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	log.info("selectCommentByIdx 리턴 {}", lifeCommentVO);
    	return lifeCommentVO;
    }
    
    /**
     * 동네생활 게시글의 댓글의 대댓글 얻기 (한번에 조회)
     * @param commentRef
     * @return
     */
    @Override
    public List<DaangnLifeCommentVO> selectLifeBoardChildComments(int commentRef){
    	log.info("selectLifeBoardChildComments 실행 commentRef => {}", commentRef);
    	List<DaangnLifeCommentVO> list = null;
    	try {
    		list = lifeBoardCommentDAO.selectLifeBoardChildComments(commentRef);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	log.info("selectLifeBoardChildComments 결과 {}개, {}", list.size(), list);
    	return list;
    }
    
    /**
     * 동네생활 댓글 가장 큰 idx 얻기
     * @return
     */
    @Override
    public int getCommentLastIdx() {
    	int result = 0;
    	try {
    		result = lifeBoardCommentDAO.getLastIdx();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return result;
    }
    
    /**
     * 동네생활 게시글 댓글 쓰기 (boardRef, comment, parentComIdx[null])
     * @param lifeCommentVO
     * @return
     */
    @Override
    @Transactional
    public int insertLifeBoardComment(DaangnLifeCommentVO lifeCommentVO) {
    	log.info("insertLifeBoardComment 실행 {}", lifeCommentVO);
    	int result = 0;
        try {
        	lifeBoardCommentDAO.insertLifeBoardComment(lifeCommentVO); // 댓글 저장
        	lifeBoardDAO.incrementCommentCount(lifeCommentVO.getBoardRef()); // 댓글 수 증가
        	if(lifeCommentVO.getParentComIdx() != null) { // 대댓글인 경우
        		lifeBoardCommentDAO.incrementChildCommentCount(lifeCommentVO.getParentComIdx()); // 댓글의 대댓글수 증가
        	}
        	result = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("insertLifeBoardComment 리턴 => {}", result);
        return result;
    }
    
    /**
     * 동네생활 게시글 댓글 수정 (idx, comment)
     * @param lifeCommentVO
     * @return
     */
    @Override
    public int updateLifeBoardComment(DaangnLifeCommentVO lifeCommentVO) {
    	log.info("updateLifeBoardComment 실행 {}", lifeCommentVO);
    	int result = 0;
    	try {
    		lifeBoardCommentDAO.updateLifeBoardComment(lifeCommentVO);
    		result = 1;
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	log.info("updateLifeBoardComment 리턴 => {}", result);
    	return result;
    }
    
    /**
     * 동네생활 게시물 댓글 삭제
     * @param commentRef
     * @param boardRef
     * @return
     */
    @Override
    @Transactional
    public int deleteLifeBoardComment(int commentRef, int boardRef, Integer parentComIdx) {
    	log.info("deleteLifeBoardComment 실행 (commentRef, boardRef, parentComIdx) => ({}, {}, {})", commentRef, boardRef, parentComIdx);
    	int result = 0;
        try {
        	// 삭제할 댓글의 정보를 가져온다.
            DaangnLifeCommentVO deletedComment = lifeBoardCommentDAO.selectCommentByIdx(commentRef);
            lifeBoardCommentDAO.deleteLifeBoardComment(commentRef); // 댓글 삭제

            // 대댓글인 경우
            if (parentComIdx != null) {
            	// 댓글의 대댓글 수 감소
            	lifeBoardCommentDAO.decrementChildCommentCount(parentComIdx);
            }
            
            // 게시물의 댓글 수 감소
            int commentCount = 1; // 삭제될 댓글 자체의 수
            if (deletedComment.getChildCommentCount() > 0) {
                commentCount += deletedComment.getChildCommentCount(); // 자식 댓글 수
            }
            log.info("commentCount ====> {}", commentCount);
            lifeBoardDAO.decrementCommentCount(boardRef, commentCount); // 게시물 댓글수 감소
            result = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("deleteLifeBoardComment 리턴 => {}", result);
        return result;
    }
    
    /**
     *  동네생활 게시물 댓글 좋아요
     * @param commentRef
     * @param userRef
     * @return
     */
    @Override
    @Transactional
    public int incrementCommentLikeCount(int userRef, int commentRef) {
    	int result = 0;
        try {
        	lifeBoardCommentLikeDAO.insertLifeBoardCommentLike(userRef, commentRef); // 댓글 좋아요 저장
        	lifeBoardCommentDAO.incrementLikeCount(commentRef); // 댓글 좋아요 수 증가
        	result = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 동네생활 게시물 댓글 좋아요 취소
     * @param commentRef
     * @param userRef
     * @return
     */
    @Override
    @Transactional
    public int decrementCommentLikeCount(int userRef, int commentRef) {
    	int result = 0;
    	try {
    		lifeBoardCommentLikeDAO.deleteLifeBoardCommentLike(userRef, commentRef); // 댓글 좋아요 삭제
    		lifeBoardCommentDAO.decrementLikeCount(commentRef); // 댓글 좋아요 수 감소
    		result = 1;
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return result;
    }
    
    /**
     * 동네생활 유저가 쓴 댓글 수 얻기
     * @param userRef
     * @return
     */
    @Override
    public int getCommentCountByUserRef(int userRef) {
    	int result = 0;
    	try {
			result = lifeBoardCommentDAO.getTotalCountByUserRef(userRef);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return result;
    }
    
    /**
     * 동네생활 댓글쓴 게시글 얻기
     * @param sv (lastItemIdx, sizeOfPage, userRef)
     * @return
     */
    @Override
    public List<DaangnLifeBoardVO> getCommentedBoardByUserRef(ScrollVO sv) {
    	List<DaangnLifeBoardVO> list = null;
    	try {
			list = lifeBoardCommentDAO.selectCommentedBoardsByUserRef(sv.getLastItemIdx(), sv.getSizeOfPage(), sv.getUserRef());
			for(DaangnLifeBoardVO boardVO : list) {
    			boardVO.setFileList(lifeBoardFileDAO.selectLifeBoardFileByBoardRef(boardVO.getIdx())); // 게시글 사진 넣어주기
    		}
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    	return list;
    }

    /**
     * 동네생활 좋아요 가장 큰 idx 얻기
     * @param userRef
     * @return
     */
    @Override
    public int getLikeLastItemIdx() {
        int result = 0;
        try {
            result = lifeBoardLikeDAO.getLastIdx();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 동네생활 유저가 누른 좋아요 수 얻기
     * @param userRef
     * @return
     */
    @Override
    public int getLikeCountByUserRef(int userRef) {
        int result = 0;
        try {
            result = lifeBoardLikeDAO.getTotalCountByUserRef(userRef);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 동네생활 유저가 좋아요한 게시글 얻기
     * @param lastItemIdx
     * @param sizeOfPage
     * @param userRef
     * @return
     */
    @Override
    public List<DaangnLifeBoardVO> getLikedBoardsByUserRef(ScrollVO sv) {
        List<DaangnLifeBoardVO> list = null;
        try {
            list = lifeBoardLikeDAO.selectLikedBoardsByUserRef(sv.getLastItemIdx(), sv.getSizeOfPage(), sv.getUserRef());
            for(DaangnLifeBoardVO boardVO : list) {
                boardVO.setFileList(lifeBoardFileDAO.selectLifeBoardFileByBoardRef(boardVO.getIdx())); // 게시글 사진 넣어주기
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // =================================================================================================================================
    // 좋아요 했는지 안했는지 확인
    // =================================================================================================================================
    
    /**
     * 동네생활 게시물 좋아요 확인
     * @param userRef
     * @param boardRef
     * @return
     */
    @Override
    public int isLifeBoardLike(int userRef, int boardRef) {
    	int result = 0;
    	try {
			result = lifeBoardLikeDAO.selectLifeBoardLike(userRef, boardRef);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return result;
    }
    
    /**
     * 동네생활 게시물 댓글 좋아요 확인
     * @param userRef
     * @param boardRef
     * @return
     */
    @Override
    public int isLifeCommentLike(int userRef, int commentRef) {
    	int result = 0;
    	try {
    		result = lifeBoardCommentLikeDAO.selectLifeBoardCommentLike(userRef, commentRef);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return result;
    }
    
    /**
     * 동네생활 게시물 사진 저장
     * @param lifeBoardFileVO
     * @return
     */
    @Override
    public int insertLifeBoardFile(DaangnLifeBoardFileVO lifeBoardFileVO) {
    	int result = 0;
    	try {
			lifeBoardFileDAO.insertLifeBoardFile(lifeBoardFileVO);
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return result;
    }
    
    /**
     * 동네생활 게시물 사진 삭제(게시글에 해당하는)
     * @param boardRef
     * @return
     */
    @Override
    public int deleteLifeBoardFile(int boardRef) {
    	int result = 0;
    	try {
    		lifeBoardFileDAO.deleteLifeBoardFileByBoardRef(boardRef);
    		result = 1;
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return result;
    }
    
    /**
     * 검색상태에 따른 갯수 얻기(categoryRef, region, gu, dong, search)
     * @param categoryRef
     * @param region
     * @param gu
     * @param dong
     * @param search
     * @return
     */
	@Override
	public int getBoardCountBy(Integer categoryRef, String region, String gu, String dong, String search) {
		int result = 0;
		try {
			result = lifeBoardDAO.getTotalCountBy(categoryRef, region, gu, dong, search);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
