package kr.ezen.daangn.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ezen.daangn.dao.DaangnBoardFileDAO;
import kr.ezen.daangn.dao.DaangnLikeDAO;
import kr.ezen.daangn.vo.CommonVO;
import kr.ezen.daangn.vo.DaangnLikeVO;
import kr.ezen.daangn.vo.DaangnMainBoardVO;
import kr.ezen.daangn.vo.PagingVO;

@Service(value = "daangnLikeService")
@Transactional
public class DaangnLikeServiceImpl implements DaangnLikeService{
	
	@Autowired
	private DaangnLikeDAO daangnLikeDAO;
	@Autowired
	private DaangnBoardFileDAO daangnBoardFileDAO;
	@Autowired
	private DaangnMemberService daangnMemberService;
	
	@Override
	public int countLike(int boardIdx) {
		int result = 0;
		try {
			result = daangnLikeDAO.countLike(boardIdx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insertLike(DaangnLikeVO likeVO) {
		int result = 0;
		try {
			daangnLikeDAO.insertLike(likeVO);
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteLike(DaangnLikeVO likeVO) {
		int result = 0;
		try {
			daangnLikeDAO.deleteLike(likeVO);
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	
	
	
	
	@Override
	public PagingVO<DaangnMainBoardVO> selectLikeByUseridx(CommonVO cv) {
		PagingVO<DaangnMainBoardVO> pv = null;
		try {
			int totalCount = daangnLikeDAO.selectLikeCountByUseridx(cv.getUserRef());
			pv = new PagingVO<>(totalCount, cv.getCurrentPage(), cv.getSizeOfPage(), cv.getSizeOfBlock());
			HashMap<String, Integer> map = new HashMap<>();
			map.put("userIdx", cv.getUserRef());
			map.put("startNo", pv.getStartNo() - 1);
			map.put("sizeOfPage", pv.getSizeOfPage());
			List<DaangnMainBoardVO> list = daangnLikeDAO.selectLikeByUseridx(map);
			for(DaangnMainBoardVO boardVO : list) {
				if(boardVO != null) {
					boardVO.setBoardFileList(daangnBoardFileDAO.selectFileByBoardIdx(boardVO.getIdx()));
					boardVO.setMember(daangnMemberService.selectByIdx(boardVO.getUserRef()));
				}
			}
			pv.setList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pv;
	}

	@Override
	public int select(DaangnLikeVO likeVO) {
		int result = 0;
		try {
			result = daangnLikeDAO.select(likeVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
