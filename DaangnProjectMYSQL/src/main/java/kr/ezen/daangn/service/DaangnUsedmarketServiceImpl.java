package kr.ezen.daangn.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ezen.daangn.dao.DaangnUsedmarketBoardDAO;
import kr.ezen.daangn.dao.DaangnUsedmarketBoardFileDAO;
import kr.ezen.daangn.dao.DaangnUsedmarketBoardLikeDAO;
import kr.ezen.daangn.dao.DaangnUsedmarketBoardReserveDAO;
import kr.ezen.daangn.vo.DaangnUsedmarketBoardFileVO;
import kr.ezen.daangn.vo.DaangnUsedmarketBoardVO;
import kr.ezen.daangn.vo.DaangnUsedmarketReserveVO;
import kr.ezen.daangn.vo.ScrollVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(value = "daangnUsedmarketService")
public class DaangnUsedmarketServiceImpl implements DaangnUsedmarketService{
	
	@Autowired
	private DaangnUsedmarketBoardDAO boardDAO;
	@Autowired
	private DaangnUsedmarketBoardFileDAO boardFileDAO;
	@Autowired
	private DaangnUsedmarketBoardLikeDAO boardLikeDAO;
	@Autowired
	private DaangnUsedmarketBoardReserveDAO reserveDAO;
	
	/**
	 * 중고거래 게시글 목록 얻기 
	 * 1. 게시글 목록보기인 경우 (lastItemIdx, sizeOfPage, categoryRef, region, gu, dong, search, boardRef)
	 * 2. 유저가 쓴글 보기인경우 (lastItemIdx, sizeOfPage, userRef, statusRef)
	 * @param sv
	 * @return
	 */
	@Override
	public List<DaangnUsedmarketBoardVO> getUsedmarketBoards(ScrollVO sv) {
		log.info("selectPagedLifeBoards 실행 (lastItemIdx, sizeOfPage, categoryRef, region, gu, dong, search) => ({}, {}, {}, {}, {}, {}, {})",sv.getLastItemIdx(), sv.getSizeOfPage(), sv.getCategoryRef(), sv.getRegion(), sv.getGu(), sv.getDong(), sv.getSearch());
		List<DaangnUsedmarketBoardVO> list = null;
		try {
			list = boardDAO.selectPagedLifeBoards(
					sv.getLastItemIdx(), sv.getSizeOfPage(),
					sv.getCategoryRef(), sv.getStatusRef(),
					sv.getRegion(), sv.getGu(), sv.getDong(), sv.getSearch(), sv.getUserRef(), sv.getBoardRef());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 유저의 판매상품의 상태에 따른 갯수 얻기 (userRef, statusRef)
	 * @param sv
	 * @return
	 */
	@Override
	public int getBoardCountBy(int userRef, int statusRef) {
		int result = 0;
		try {
			result = boardDAO.getTotalCountByUserRefAndStatusRef(userRef, statusRef);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 검색상태에 따른 갯수 얻기 (categoryRef, region, gu, dong, search)
	 * @param sv
	 * @return
	 */
	@Override
	public int getBoardCountBy(Integer categoryRef, String region, String gu, String dong, String search) {
		int result = 0;
		try {
			result = boardDAO.getTotalCountBy(categoryRef, region, gu, dong, search);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 중고거래 게시글 가장 큰 idx 얻기
	 * @return
	 */
	@Override
	public int getBoardLastIdx() {
		int result = 0;
		try {
			result = boardDAO.getLastIdx();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 중고거래 게시글 한개 얻기
	 * @param idx
	 * @return
	 */
	@Override
	public DaangnUsedmarketBoardVO selectByIdx(int idx) {
		log.info("selectByIdx 실행 idx => {}", idx);
		DaangnUsedmarketBoardVO boardVO = null;
		try {
			boardVO = boardDAO.selectByIdx(idx);
			if(boardVO != null) {
				boardVO.setBoardFileList(boardFileDAO.selectUsedmarketBoardFileByBoardRef(idx));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("selectByIdx 리턴 {}", boardVO);
		return boardVO;
	}
	
	/**
	 * 중고거래 게시글 쓰기
	 * @param testVO
	 * @return
	 */
	@Override
	public int insertUsedmarketBoard(DaangnUsedmarketBoardVO boardVO) {
		log.info("insertUsedmarketBoard 실행 {}", boardVO);
		int result = 0;
		try {
			boardDAO.insertUsedmarketBoard(boardVO);
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("insertUsedmarketBoard 리턴 => {}", result);
		return result;
	}
	
	/**
	 * 중고거래 게시글 수정하기
	 * @param testVO
	 * @return
	 */
	@Override
	public int updateUsedmarketBoard(DaangnUsedmarketBoardVO boardVO) {
		log.info("updateUsedmarketBoard 실행 {}", boardVO);
		int result = 0;
        try {
        	boardDAO.updateUsedmarketBoard(boardVO);
        	result = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("updateUsedmarketBoard 리턴 => {}", result);
        return result;
	}
	
	/**
	 * 중고거래 게시글 삭제하기
	 * @param idx
	 * @return
	 */
	@Override
	public int deleteUsedmarketBoard(int idx) {
		log.info("deleteUsedmarketBoard 실행 {}", idx);
    	int result = 0;
        try {
        	boardDAO.deleteUsedmarketBoard(idx);
        	result = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("deleteUsedmarketBoard 리턴 => {}", result);
        return result;
	}
	
	/**
	 * 중고거래 게시글 조회수 증가
	 * @param idx
	 * @return
	 */
	@Override
	public int incrementReadCount(int idx) {
		int result = 0;
        try {
        	boardDAO.incrementReadCount(idx);
        	result = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
	}
	
	/**
	 * 게시글 상태 변경하기
	 * @param boardRef
	 * @param statusRef
	 * @return
	 */
	@Override
	@Transactional
	public int updateStatus(int boardRef, Integer userRef, int statusRef) {
		int result = 0;
        try {
        	boardDAO.updateUsedmarketBoardStatus(boardRef, statusRef);
        	switch (statusRef) {
	            case 1: {
	                reserveDAO.deleteReserveByboardRef(boardRef); // 예약 삭제
	                break;
	            }
	            case 2: {
	                var reserveVO = new DaangnUsedmarketReserveVO();
	                reserveVO.setBoardRef(boardRef);
	                reserveVO.setUserRef(userRef);
	                reserveVO.setInteraction(0);
	                reserveDAO.insertReserve(reserveVO); // 예약 저장
	                break;
	            }
	            case 3: {
	                reserveDAO.deleteReserveByboardRef(boardRef);
	                var reserveVO = new DaangnUsedmarketReserveVO();
	                reserveVO.setBoardRef(boardRef);
	                reserveVO.setUserRef(userRef);
	                reserveVO.setInteraction(1);
	                reserveDAO.insertReserve(reserveVO); // 구매 완료 저장
	                break;
	            }
	        }
        	result = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
	}
	
	
	
	
	
	/**
	 * 게시글에 해당하는 유저의 다른 게시물 얻기 (userRef, boardRef)
	 * @param sv
	 * @return
	 */
	@Override
	public List<DaangnUsedmarketBoardVO> selectListByUserIdxAndNotBoardIdx(int userRef, int boardRef) {
		List<DaangnUsedmarketBoardVO> list = null;
		try {
			list = boardDAO.selectPagedLifeBoards(boardDAO.getLastIdx() + 1, 2, null, null, null, null, null, null, userRef, boardRef);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 중고거래 게시물 사진 저장
	 * @param usedmarketBoardFileVO
	 * @return
	 */
	@Override
	public int insertUsedmarketBoardFile(DaangnUsedmarketBoardFileVO usedmarketBoardFileVO) {
		int result = 0;
		try {
			boardFileDAO.insertUsedmarketBoardFile(usedmarketBoardFileVO);
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 중고거래 게시물 사진 삭제(게시글에 해당하는)
	 * @param boardRef
	 * @return
	 */
	@Override
	public int deleteUsedmarketBoardFile(int boardRef) {
		int result = 0;
		try {
			boardFileDAO.deleteUsedmarketBoardFileByBoardRef(boardRef);
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//=========================================================================
	// 좋아요 관련
	//=========================================================================
	/**
	 * 중고거래 게시글 찜하기
	 * @param boardRef
	 * @param userRef
	 * @return
	 */
	@Override
	@Transactional
	public int incrementLikeCount(int boardRef, int userRef) {
		int result = 0;
		try {
			boardDAO.incrementLikeCount(boardRef); // 좋아요 수 증가
			boardLikeDAO.insertUsedmarketBoardLike(userRef, boardRef); // 좋아요 저장
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 중고거래 게시글 찜 취소하기
	 * @param boardRef
	 * @param userRef
	 * @return
	 */
	@Override
	public int decrementLikeCount(int boardRef, int userRef) {
		int result = 0;
		try {
			boardDAO.decrementLikeCount(boardRef); // 좋아요 수 감소
			boardLikeDAO.deleteUsedmarketBoardLike(userRef, boardRef); // 좋아요 삭제
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 중고거래 게시물 좋아요 확인
	 * @param userRef
	 * @param boardRef
	 * @return
	 */
	@Override
	public int isUsedmarketBoardLike(int userRef, int boardRef) {
		int result = 0;
		try {
			result = boardLikeDAO.selectUsedmarketBoardLike(userRef, boardRef);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 좋아요한 목록 얻기 (lastItemIdx, sizeOfPage, userRef)
	 * @param sv
	 * @return
	 */
	@Override
	public List<DaangnUsedmarketBoardVO> getLikeBoardsByUserRef(ScrollVO sv) {
		List<DaangnUsedmarketBoardVO> list = null;
		try {
			list = boardLikeDAO.selectLikeBoardsByUserRef(sv.getLastItemIdx(), sv.getSizeOfPage(), sv.getUserRef());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 좋아요 한 목록 총합 얻기
	 * @param userRef
	 * @return
	 */
	@Override
	public int getLikeBoardsSizeByUserRef(int userRef) {
		int result = 0;
		try {
			result = boardLikeDAO.getLikeBoardTotalCountByUserRef(userRef);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 좋아요 최대 idx 얻기
	 * @return
	 */
	@Override
	public int getLikeLastItemIdx() {
		int result = 0;
        try {
            result = boardLikeDAO.getLastItemIdx();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
	}
	
	//=========================================================================
	// 예약 관련
	//=========================================================================
	/**
	 * 예약자가 있는지 확인하기
	 * @param boardRef
	 * @return
	 */
	@Override
	public DaangnUsedmarketReserveVO getReserveByBoardRef(int boardRef) {
		DaangnUsedmarketReserveVO rv = null;
		try {
			rv = reserveDAO.getReserveByBoardRef(boardRef);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rv;
	}
	
	/**
	 * 구매 목록 얻기 (lastItemIdx, sizeOfPage, userRef)
	 * @param sv
	 * @return
	 */
	@Override
	public List<DaangnUsedmarketBoardVO> getPurchaseListByUserRef(ScrollVO sv){
		List<DaangnUsedmarketBoardVO> list = null;
		try {
			list = reserveDAO.selectPurchaseListByUserRef(sv.getLastItemIdx(), sv.getSizeOfPage(), sv.getUserRef());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 구매 목록 총합 얻기
	 * @param userRef
	 * @return
	 */
	@Override
	public int getPurchaseListSizeByUserRef(int userRef) {
		int result = 0;
		try {
			result = reserveDAO.getPurchaseListTotalCountByUserRef(userRef);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 구매목록 최대 idx 얻기
	 * @return
	 */
	@Override
	public int getReserveLastItemIdx() {
		int result = 0;
        try {
            result = reserveDAO.getLastItemIdx();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
	}
}
