package kr.ezen.daangn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.ezen.daangn.service.DaangnUsedmarketService;
import kr.ezen.daangn.vo.DaangnMemberVO;
import kr.ezen.daangn.vo.DaangnUsedmarketBoardVO;
import kr.ezen.daangn.vo.ScrollVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/used-market")
public class UsedmarketController {
	
	@Autowired
	private DaangnUsedmarketService usedmarketService;
	
	/**
	 * 중고거래 게시물 목록 보기
	 * @param model
	 * @param session
	 * @param region
	 * @param gu
	 * @param dong
	 * @param search
	 * @return
	 */
	@GetMapping(value = {"/view", "/view/", "/view/{region}", "/view/{region}/{gu}", "/view/{region}/{gu}/{dong}"})
	public String usedview( Model model,
							HttpSession session,
							@PathVariable(value = "region", required = false) String region,
							@PathVariable(value = "gu", required = false) String gu,
							@PathVariable(value = "dong", required = false) String dong,
							@RequestParam(value = "categoryRef", required = false) Integer categoryRef,
							@RequestParam(value = "search", required = false) String search) {
		log.debug("lifeview 실행 region: {}, gu: {}, dong: {}, search: {}", region, gu, dong, search);
		if(region != null) {
			model.addAttribute("region", region);		
		}
		if(gu != null) {
			model.addAttribute("gu", gu);			
		}
		if(dong != null) {
			model.addAttribute("dong", dong);
		}
		if(categoryRef != null && categoryRef > 0) {
			model.addAttribute("categoryRef", categoryRef);
		}
		if(search != null && !search.equals("")) {
			model.addAttribute("search", search);
		}
		model.addAttribute("lastItemIdx", usedmarketService.getBoardLastIdx() + 1);
		return "usedmarket/usedmarket";
	}
	
	/**
	 * 중고거래 게시글 리턴 (lastItemIdx, sizeOfPage, categoryRef, region, gu, dong, search, userRef)
	 * @param sv
	 * @return
	 */
	@PostMapping(value = "/list")
	@ResponseBody
	public List<DaangnUsedmarketBoardVO> getUsedmarketBoardList(@RequestBody ScrollVO sv){
		log.info("getLifeBoardList 실행 : {}", sv);
		List<DaangnUsedmarketBoardVO> list = usedmarketService.getUsedmarketBoards(sv);
		return list;
	}
	
	/**
	 * 중고거래 게시물 상세 보기
	 * @param model
	 * @param request
	 * @param response
	 * @param idx
	 * @return
	 */
	@GetMapping(value = "/detail/{idx}")
	public String usedmarketDetail(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "idx") int idx){
		log.info("usedmarketDetail/idx 실행 => idx : {}", idx);
		DaangnUsedmarketBoardVO board = usedmarketService.selectByIdx(idx);
		if(board == null) {
			return "redirect:/life/view";
		}
		DaangnMemberVO user = (DaangnMemberVO) request.getSession().getAttribute("user");
		if(user != null) { // 로그인 상태이면
			int isLiked = usedmarketService.isUsedmarketBoardLike(user.getIdx(), idx);
			board.setIsLike(isLiked); // 좋아요했는지 확인
			model.addAttribute("isLogin", true);
		} else {
			model.addAttribute("isLogin", false);
		}
		// 조회수 증가로직 쿠키 방식
		Cookie oldCookie = null;
		Cookie[] cookies = request.getCookies();
		final String COOKIE_NAME = "life";
		final int COOKIE_MAX_AGE = 60; // 쿠키지속시간

		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        if (cookie.getName().equals(COOKIE_NAME)) {
		            oldCookie = cookie;
		            break;
		        }
		    }
		}

		if (oldCookie != null) {
		    String cookieValue = oldCookie.getValue();
		    if (!cookieValue.contains("[" + idx + "]")) {
		    	usedmarketService.incrementReadCount(idx);
		        board.setReadCount(board.getReadCount() + 1);
		        oldCookie.setValue(cookieValue + "_[" + idx + "]");
		        oldCookie.setMaxAge(COOKIE_MAX_AGE);
		        response.addCookie(oldCookie);
		    }
		} else {
			usedmarketService.incrementReadCount(idx);
			board.setReadCount(board.getReadCount() + 1);
		    Cookie newCookie = new Cookie(COOKIE_NAME, "[" + idx + "]");
		    newCookie.setMaxAge(COOKIE_MAX_AGE);
		    response.addCookie(newCookie);
		}
		
		model.addAttribute("board", board);
		return "usedmarket/usedDetail";
	}
	
	/**
	 * 중고거래 게시물 쓰기 페이지
	 * @return
	 */
	@GetMapping("/board/write")
	public String insertUsedBoard() { // 시큐리티가 알아서 해줄꺼같다.
		return "usedmarket/usedUpload";
	}
}
