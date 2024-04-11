package kr.ezen.daangn.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.ezen.daangn.service.DaangnMemberService;
import kr.ezen.daangn.service.DaangnUsedmarektChatService;
import kr.ezen.daangn.service.DaangnUsedmarketService;
import kr.ezen.daangn.vo.DaangnMemberVO;
import kr.ezen.daangn.vo.DaangnUsedmarketBoardFileVO;
import kr.ezen.daangn.vo.DaangnUsedmarketBoardVO;
import kr.ezen.daangn.vo.ScrollVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/used-market")
public class UsedmarketController {
	
	@Autowired
	private DaangnUsedmarketService usedmarketService;
	@Autowired
	private DaangnMemberService memberService;
	@Autowired
	private DaangnUsedmarektChatService chatService;
	
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
		model.addAttribute("totalCount", usedmarketService.getBoardCountBy(categoryRef, region, gu, dong, search));
		return "usedmarket/usedmarket";
	}
	
	/**
	 * 중고거래 게시글 리턴
	 * 1. (lastItemIdx, sizeOfPage, categoryRef, region, gu, dong, search, boardRef)
	 * 2. (lastItemIdx, sizeOfPage, userRef, statusRef)
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
			return "redirect:/used-market/view";
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
		// 이 글의 유저가 쓴 다른 글들!;
		model.addAttribute("userBoard", usedmarketService.selectListByUserIdxAndNotBoardIdx(board.getUserRef(), board.getIdx()));
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
	
	
	/**
	 * 중고거래 게시물 쓰기 ok
	 * @param lifeBoardVO
	 * @return
	 */
	@PostMapping(value = "/board/write/ok", consumes = "multipart/form-data; charset=utf-8")
	@ResponseBody
	@Transactional
	public String insertUsedmarketBoardOk(MultipartHttpServletRequest request, @ModelAttribute DaangnUsedmarketBoardVO boardVO) {
		DaangnMemberVO user = (DaangnMemberVO) request.getSession().getAttribute("user");
		if(user == null) {
			return "0";
		}
		boardVO.setUserRef(user.getIdx());
		String ipAddress = request.getRemoteAddr(); // 클라이언트의 IP 주소 가져오기
	    boardVO.setIp(ipAddress);
	    String uploadPath = request.getServletContext().getRealPath("/upload/");
	    File file2 = new File(uploadPath);
	    log.info("서버 실제 경로 : " + uploadPath);
	    
		if (!file2.exists()) {
			file2.mkdirs();
		}
		
		List<MultipartFile> list = request.getFiles("file"); // form에 있는 name과 일치
		log.info("listSize => {}개", list.size());
		boolean isFirstFile = true;
		try {
			if (list != null && list.size() > 0) {
				for(MultipartFile file :list) {
					if(file != null && file.getSize() >0) {
						String saveFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
						File savaFile = new File(uploadPath, saveFileName);
						FileCopyUtils.copy(file.getBytes(), savaFile); // 파일 저장
						
						// 첫 번째 파일일 경우 썸네일로 저장
						if(isFirstFile) {
							boardVO.setThumbnail(saveFileName);
							usedmarketService.insertUsedmarketBoard(boardVO);
							isFirstFile = false;
						}
						DaangnUsedmarketBoardFileVO boardFileVO = new DaangnUsedmarketBoardFileVO();
						boardFileVO.setBoardRef(boardVO.getIdx());
						boardFileVO.setSaveFileName(saveFileName);
						usedmarketService.insertUsedmarketBoardFile(boardFileVO);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "0"; // 실패시
		}
		log.info("저장 성공 {}", boardVO);
	    return boardVO.getIdx() + ""; // 생성된 idx리턴
	}
	
	/**
	 * 중고거래 게시글 수정 페이지
	 * @param idx
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping(value = "/board/update")
	public String updateUsedBoard(Model model,@RequestParam(value = "idx") int idx, HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/";
		}
		DaangnUsedmarketBoardVO boardVO = usedmarketService.selectByIdx(idx);
		if(boardVO == null) {
			return "redirect:/used-market/view";
		}
		log.info("updateUsedBoard 실행 idx => {}", idx);
		DaangnMemberVO user = (DaangnMemberVO) session.getAttribute("user");
		if(boardVO.getUserRef() != user.getIdx()) {
			return "redirect:/";
		}
		model.addAttribute("board", boardVO);
		return "usedmarket/usedUpdate";
	}
	/**
	 * 중고거래 게시글 수정 ok
	 * @param idx
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping(value = "/board/update/ok", consumes = "multipart/form-data; charset=utf-8")
	@ResponseBody
	@Transactional
	public String updateUsedBoardOk(MultipartHttpServletRequest request, @ModelAttribute DaangnUsedmarketBoardVO boardVO) {
		int result = 0;
		DaangnMemberVO user = (DaangnMemberVO) request.getSession().getAttribute("user");
		if(user == null) {
			return "0";
		}
		String ipAddress = request.getRemoteAddr(); // 클라이언트의 IP 주소 가져오기
		boardVO.setIp(ipAddress);
		usedmarketService.deleteUsedmarketBoardFile(boardVO.getIdx());
		
		String uploadPath = request.getServletContext().getRealPath("/upload/");
	    File file2 = new File(uploadPath);
	    log.info("서버 실제 경로 : " + uploadPath);
	    
		if (!file2.exists()) {
			file2.mkdirs();
		}
		List<MultipartFile> list = request.getFiles("file"); // form에 있는 name과 일치
		log.info("listSize => {}개", list.size());
		boolean isFirstFile = true;
		try {
			if (list != null && list.size() > 0) {
				for(MultipartFile file :list) {
					if(file != null && file.getSize() >0) {
						String saveFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
						File savaFile = new File(uploadPath, saveFileName);
						FileCopyUtils.copy(file.getBytes(), savaFile); // 파일 저장
						
						// 첫 번째 파일일 경우 썸네일로 저장
						if(isFirstFile) {
							boardVO.setThumbnail(saveFileName);
							usedmarketService.updateUsedmarketBoard(boardVO);
							isFirstFile = false;
						}
						DaangnUsedmarketBoardFileVO boardFileVO = new DaangnUsedmarketBoardFileVO();
						boardFileVO.setBoardRef(boardVO.getIdx());
						boardFileVO.setSaveFileName(saveFileName);
						usedmarketService.insertUsedmarketBoardFile(boardFileVO);
					}
				}
			}
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
			return "0"; // 실패시
		}
		return result + "";
	}
	
	/**
	 * 중고거래 게시글 삭제 ok
	 * @param lifeBoardVO
	 * @return
	 */
	@PostMapping("/board/delete")
	@ResponseBody
	public String deleteUsedBoardOk(@RequestBody DaangnUsedmarketBoardVO boardVO) {
		log.info("deleteUsedBoardOk 실행 {}", boardVO.getIdx());
	    int result = usedmarketService.deleteUsedmarketBoard(boardVO.getIdx());
	    log.info("deleteUsedBoardOk 리턴 {}", result);
	    return result + ""; // 성공 실패 리턴
	}
	
	/**
	 * 게시물 상태변경 페이지
	 * @param boardRef
	 * @param statusRef
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping(value = "/board-status/update")
	public String usedStatusUpdate(@RequestParam(value = "boardRef") int boardRef, @RequestParam(value = "statusRef") int statusRef, Model model, HttpSession session) {
		var boardVO = usedmarketService.selectByIdx(boardRef);
		var user = (DaangnMemberVO) session.getAttribute("user");
		if(boardVO.getUserRef() != user.getIdx()) {
			return "redirect:/";
		}
		log.info("usedStatusUpdate 실행 boardIdx => {}, statusRef => {}", boardRef, statusRef);
		// 1. 예약목록이 있는지 확인한다!
		var rv = usedmarketService.getReserveByBoardRef(boardRef);
		if(rv == null) {
			if(statusRef == 1) { // 예약목록이 없으면 1번 금지!
				return "redirect:/";
			}
		} else {
			if(rv.getInteraction() == 1) { // 거래완료시 상품상태 변경불가
				return "redirect:/";
			}
		}
		model.addAttribute("rv", rv); // 예약목록이 있고 interaction = 0 이면 2빼고 1,2,3 셋 다가능
		model.addAttribute("board", boardVO);
		// 1. 게시글에 해당하는 채팅유저를 가져온다.
		List<DaangnMemberVO> chatUsers = chatService.getChatRoomUserByBoardRef(boardRef);
		log.info("fleamarketStatusUpdate 리턴 chatUsers => {}개 , {}", chatUsers.size(), chatUsers);
		model.addAttribute("chatUsers", chatUsers);
		model.addAttribute("statusRef", statusRef);
		return "usedmarket/usedStatusUpdate";
	}
	
	/**
	 * 게시물 상태 변경 완료
	 * @param boardRef
	 * @param statusRef
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping(value = "/board-status/update/ok")
	@ResponseBody
	public String usedStatusUpdateOk(@RequestParam(value = "boardRef") int boardRef,
									 @RequestParam(value = "statusRef") int statusRef,
									 Model model, 
									 HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/";
		}
		var boardVO = usedmarketService.selectByIdx(boardRef);
		var user = (DaangnMemberVO) session.getAttribute("user");
		
		if(boardVO.getUserRef() != user.getIdx()) {
			return "redirect:/";
		}
		log.info("fleamarketStatusUpdateOk 실행 boardRef => {}, statusRef => {}", boardRef, statusRef);
		int result = usedmarketService.updateStatus(boardRef, user.getIdx(), statusRef);
		log.info("fleamarketStatusUpdateOk 리턴 result => {}", result);
		return result + "";
	}
	
	//================================================================================================================
	// 좋아요
	//================================================================================================================
	/**
	 * 중고거래 게시물 찜하기
	 * @param session
	 * @param boardRef
	 * @return
	 */
	@PostMapping(value = "/like")
	@ResponseBody
	public int likeLifeBoard(HttpSession session, @RequestBody ScrollVO sv) {
		log.info("좋아요 실행 {}", sv.getBoardRef());
		DaangnMemberVO user = (DaangnMemberVO) session.getAttribute("user");
		int result = usedmarketService.incrementLikeCount(sv.getBoardRef(), user.getIdx());
		log.info("result => {}", result);
		return result;
	}
	
	/**
	 * 중고거래 게시물 찜 취소
	 * @param session
	 * @param boardRef
	 * @return
	 */
	@PostMapping(value = "/unlike")
	@ResponseBody
	public int unlikeLifeBoard(HttpSession session, @RequestBody ScrollVO sv) {
		log.info("좋아요 취소 실행 {}", sv.getBoardRef());
		DaangnMemberVO user = (DaangnMemberVO) session.getAttribute("user");
	    int result = usedmarketService.decrementLikeCount(sv.getBoardRef(), user.getIdx());
	    log.info("result => {}", result);
	    return result;
	}
	//================================================================================================================
	// 좋아요 끝
	//================================================================================================================
	
	/**
	 * 유저의 글 목록 보는곳
	 * @param userIdx
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/{userIdx}/board")
	public String getBoardsByUserIdx(@PathVariable(value = "userIdx") int userIdx, Model model) {
		DaangnMemberVO user = memberService.selectByIdx(userIdx);
		model.addAttribute("user", user);
		model.addAttribute("lastItemIdx", usedmarketService.getBoardLastIdx() + 1);
		model.addAttribute("boardStatus1", usedmarketService.getBoardCountBy(user.getIdx(), 1));
    	model.addAttribute("boardStatus2", usedmarketService.getBoardCountBy(user.getIdx(), 2));
    	model.addAttribute("boardStatus3", usedmarketService.getBoardCountBy(user.getIdx(), 3));
		return "usedmarket/userView";
	}
	
	
	// 채팅 메시지는 채팅 컨트롤러로 이전하겠다.
}
