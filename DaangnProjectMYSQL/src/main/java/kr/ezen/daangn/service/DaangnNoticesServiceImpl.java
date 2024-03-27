package kr.ezen.daangn.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ezen.daangn.dao.DaangnNoticesDAO;
import kr.ezen.daangn.vo.CommonVO;
import kr.ezen.daangn.vo.NoticesVO;
import kr.ezen.daangn.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Service(value = "daangnNoticesService")
@Slf4j
public class DaangnNoticesServiceImpl implements DaangnNoticesService{
	
	@Autowired
	private DaangnNoticesDAO noticesDAO;
	
	@Override
	public PagingVO<NoticesVO> getPagingList(CommonVO cv) {
		log.info("getPagingList 실행 {}", cv);
		PagingVO<NoticesVO> pv = null;
		try {
			HashMap<String, Object> map = new HashMap<>();
			int totalCount = noticesDAO.selectCount();
			pv = new PagingVO<>(totalCount, cv.getCurrentPage(), cv.getSizeOfPage(), cv.getSizeOfBlock());
			map.put("startNo", pv.getStartNo() - 1);
			map.put("sizeOfPage", 10);
			List<NoticesVO> list = noticesDAO.selectList(map);
			pv.setList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pv;
	}

	@Override
	public NoticesVO getNoticesByIdx(int idx) {
		NoticesVO nv = null;
		try {
			nv = noticesDAO.selectByIdx(idx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nv;
	}

	@Override
	public int insertNotices(NoticesVO nv) {
		int result = 0;
		try {
			noticesDAO.insert(nv);
			log.info("nv {}", nv);
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateNotices(NoticesVO nv) {
		int result = 0;
		try {
			noticesDAO.update(nv);
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<NoticesVO> selectByHighlight() {
		List<NoticesVO> list = null;
		try {
			list = noticesDAO.selectByHighlight();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int deleteByIdx(int idx) {
		int result = 0;
		try {
			noticesDAO.deleteByIdx(idx);
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
