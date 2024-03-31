package kr.ezen.daangn.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
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
import kr.ezen.daangn.service.DaangnLifeBoardService;
import kr.ezen.daangn.vo.DaangnLifeBoardFileVO;
import kr.ezen.daangn.vo.DaangnLifeBoardVO;
import kr.ezen.daangn.vo.DaangnLifeCommentVO;
import kr.ezen.daangn.vo.DaangnMemberVO;
import kr.ezen.daangn.vo.ScrollVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/life")
public class LifeController {
	
	@Autowired
	private DaangnLifeBoardService lifeBoardService;
	
	/**
	 * 동네 생활 게시물 목록 보기
	 * @param model
	 * @param session
	 * @param region
	 * @param gu
	 * @param dong
	 * @param search
	 * @return
	 */
	@GetMapping(value = {"/view", "/view/", "/view/{region}", "/view/{region}/{gu}", "/view/{region}/{gu}/{dong}"})
	public String lifeview( Model model,
							HttpSession session,
							@PathVariable(value = "region", required = false) String region,
							@PathVariable(value = "gu", required = false) String gu,
							@PathVariable(value = "dong", required = false) String dong,
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
		if(search != null && !search.equals("")) {
			model.addAttribute("search", search);
		}
		model.addAttribute("lastItemIdx", lifeBoardService.getBoardLastIdx() + 1);
		return "life/life";
	}
	
	/**
	 * 동네 생활 게시글 리턴
	 * @param sv
	 * @return
	 */
	@GetMapping(value = "/list")
	@ResponseBody
	public List<DaangnLifeBoardVO> getLifeBoardList(@RequestBody ScrollVO sv){
		log.info("getLifeBoardList 실행 : {}", sv);
		List<DaangnLifeBoardVO> list = lifeBoardService.selectPagedLifeBoards(sv);
		return list;
	}
	
	/**
	 * 동네 생활 게시글에 해당하는 댓글 리턴
	 * @param sv
	 * @return
	 */
	@GetMapping(value = "/commentList")
	@ResponseBody
	public List<DaangnLifeCommentVO> getLifeCommentList(HttpSession session, @RequestBody ScrollVO sv){
		log.info("getLifeCommentList 실행 : {}", sv);
		List<DaangnLifeCommentVO> list = lifeBoardService.selectPagedLifeBoardComments(sv);
		DaangnMemberVO user = (DaangnMemberVO) session.getAttribute("user");
		if(user != null) { // 로그인 상태이면
			for(DaangnLifeCommentVO commentVO : list) { // 좋아요했는지 확인
				int isLike = lifeBoardService.isLifeCommentLike(user.getIdx(), commentVO.getIdx());
				commentVO.setIsLike(isLike);
			}			
		}
		return list;
	}
	
	/**
	 * 동네 생활 댓글에 해당하는 대댓글 리턴
	 * @param commentRef
	 * @return
	 */
	@GetMapping(value = "/childCommentList")
	@ResponseBody
	public List<DaangnLifeCommentVO> getLifeChildCommentList(HttpSession session, @RequestParam(value = "commentRef") int commentRef){
		log.info("getLifeChildCommentList 실행 : {}", commentRef);
		List<DaangnLifeCommentVO> list = lifeBoardService.selectLifeBoardChildComments(commentRef);
		DaangnMemberVO user = (DaangnMemberVO) session.getAttribute("user");
		if(user != null) { // 로그인 상태이면
			for(DaangnLifeCommentVO commentVO : list) { // 좋아요했는지 확인
				int isLike = lifeBoardService.isLifeCommentLike(user.getIdx(), commentVO.getIdx());
				commentVO.setIsLike(isLike);
			}
		}
		return list;
	}
	
	/**
	 * 동네 생활 게시물 상세 보기
	 * @param model
	 * @param request
	 * @param response
	 * @param idx
	 * @return
	 */
	@GetMapping(value = "/detail/{idx}")
	public String lifeDetail(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "idx") int idx){
		log.info("lifeDetail/idx 실행 => idx : {}", idx);
		DaangnLifeBoardVO board = lifeBoardService.selectByIdx(idx);
		if(board == null) {
			return "redirect:/life";
		}
		DaangnMemberVO user = (DaangnMemberVO) request.getSession().getAttribute("user");
		if(user != null) { // 로그인 상태이면
			int isLiked = lifeBoardService.isLifeBoardLike(user.getIdx(), idx);
			board.setIsLike(isLiked); // 좋아요했는지 확인
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
		        lifeBoardService.incrementReadCount(idx);
		        board.setReadCount(board.getReadCount() + 1);
		        oldCookie.setValue(cookieValue + "_[" + idx + "]");
		        oldCookie.setMaxAge(COOKIE_MAX_AGE);
		        response.addCookie(oldCookie);
		    }
		} else {
			lifeBoardService.incrementReadCount(idx);
			board.setReadCount(board.getReadCount() + 1);
		    Cookie newCookie = new Cookie(COOKIE_NAME, "[" + idx + "]");
		    newCookie.setMaxAge(COOKIE_MAX_AGE);
		    response.addCookie(newCookie);
		}
		
		model.addAttribute("board", board);
		model.addAttribute("lastItemIdx", lifeBoardService.getCommentLastIdx() + 1); // 댓글의 idx
		return "life/lifeDetail";
	}
	
	
	/**
	 * 게시물 쓰기 페이지
	 * @param session
	 * @return
	 */
	@GetMapping("/board/write")
	public String insertLifeBoard() { // 시큐리티가 알아서 해줄꺼같다.
		return "life/lifeUpload";
	}
	
	/**
	 * 동네생활 게시물 쓰기 ok
	 * @param lifeBoardVO
	 * @return
	 */
	@PostMapping("/board/write/ok")
	@ResponseBody
	public String insertLifeBoardOk(MultipartHttpServletRequest request, @RequestBody DaangnLifeBoardVO lifeBoardVO) {
		DaangnMemberVO user = (DaangnMemberVO) request.getSession().getAttribute("user");
		if(user == null) {
			return "0";
		}
		lifeBoardVO.setUserRef(user.getIdx());
		String ipAddress = request.getRemoteAddr(); // 클라이언트의 IP 주소 가져오기
	    lifeBoardVO.setIp(ipAddress);
	    lifeBoardService.insertLifeBoard(lifeBoardVO);
	    String uploadPath = request.getServletContext().getRealPath("/upload/");
	    File file2 = new File(uploadPath);
	    log.info("서버 실제 경로 : " + uploadPath);
	    
		if (!file2.exists()) {
			file2.mkdirs();
		}
		
		List<MultipartFile> list = request.getFiles("file"); // form에 있는 name과 일치
		try {
			if (list != null && list.size() > 0) {
				for(MultipartFile file :list) {
					if(file != null && file.getSize() >0) {
						String saveFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
						File savaFile = new File(uploadPath, saveFileName);
						FileCopyUtils.copy(file.getBytes(), savaFile); // 파일 저장
						DaangnLifeBoardFileVO lifeBoardFileVO = new DaangnLifeBoardFileVO();
						lifeBoardFileVO.setBoardRef(lifeBoardVO.getIdx());
						lifeBoardFileVO.setSaveFileName(saveFileName);
						lifeBoardService.insertLifeBoardFile(lifeBoardFileVO);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return lifeBoardVO.getIdx() + ""; // 생성된 idx리턴
	}
	
	/**
	 * 동네생활 게시글 수정 페이지
	 * @param model
	 * @param idx
	 * @return
	 */
	@PostMapping("/board/update")
	public String updateLifeBoard(Model model, @RequestParam("idx") int idx) {
		DaangnLifeBoardVO boardVO = lifeBoardService.selectByIdx(idx);
		if(boardVO == null) {
			return "redirect:/life/view";
		}
		model.addAttribute("board", boardVO);
		return "life/lifeUpdate";
	}
	/**
	 * 동네생활 게시글 수정 ok
	 * @param request
	 * @param lifeBoardVO
	 * @return
	 */
	@PostMapping("/board/update/ok")
	@ResponseBody
	public String updateLifeBoardOk(MultipartHttpServletRequest request, @RequestBody DaangnLifeBoardVO lifeBoardVO) {
		int result = 0;
		DaangnMemberVO user = (DaangnMemberVO) request.getSession().getAttribute("user");
		if(user == null) {
			return "0";
		}
		lifeBoardVO.setUserRef(user.getIdx());
		String ipAddress = request.getRemoteAddr(); // 클라이언트의 IP 주소 가져오기
	    lifeBoardVO.setIp(ipAddress);
	    lifeBoardService.updateLifeBoard(lifeBoardVO);
	    lifeBoardService.deleteLifeBoardFile(lifeBoardVO.getIdx()); // 이전 사진들 삭제
	    String uploadPath = request.getServletContext().getRealPath("/upload/");
	    File file2 = new File(uploadPath);
	    log.info("서버 실제 경로 : " + uploadPath);
	    
		if (!file2.exists()) {
			file2.mkdirs();
		}
		
		List<MultipartFile> list = request.getFiles("file"); // form에 있는 name과 일치
		try {
			if (list != null && list.size() > 0) {
				for(MultipartFile file :list) {
					if(file != null && file.getSize() >0) {
						String saveFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
						File savaFile = new File(uploadPath, saveFileName);
						FileCopyUtils.copy(file.getBytes(), savaFile); // 파일 저장
						DaangnLifeBoardFileVO lifeBoardFileVO = new DaangnLifeBoardFileVO();
						lifeBoardFileVO.setBoardRef(lifeBoardVO.getIdx());
						lifeBoardFileVO.setSaveFileName(saveFileName);
						lifeBoardService.insertLifeBoardFile(lifeBoardFileVO);
					}
				}
			}
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return result + ""; // 성공 실패 리턴
	}
	
	/**
	 * 동네생활 게시글 삭제 ok
	 * @param lifeBoardVO
	 * @return
	 */
	@PostMapping("/board/delete")
	@ResponseBody
	public String deleteLifeBoardOk(@RequestBody DaangnLifeBoardVO lifeBoardVO) {
	    int result = lifeBoardService.deleteLifeBoard(lifeBoardVO.getIdx());
	    return result + ""; // 성공 실패 리턴
	}
	
	/**
	 * 동네생활 게시물 좋아요
	 * @param session
	 * @param boardRef
	 * @return
	 */
	@PostMapping(value = "/like")
	@ResponseBody
	public int likeLifeBoard(HttpSession session, @RequestParam("boardRef") int boardRef) {
		DaangnMemberVO user = (DaangnMemberVO) session.getAttribute("user");
		int result = lifeBoardService.incrementLikeCount(boardRef, user.getIdx());
		return result;
	}
	
	/**
	 * 동네생활 게시물 좋아요 취소
	 * @param session
	 * @param boardRef
	 * @return
	 */
	@PostMapping(value = "/unlike")
	@ResponseBody
	public int unlikeLifeBoard(HttpSession session, @RequestParam("boardRef") int boardRef) {
		DaangnMemberVO user = (DaangnMemberVO) session.getAttribute("user");
	    int result = lifeBoardService.decrementLikeCount(boardRef, user.getIdx());
	    return result;
	}
	
	/**
	 * 동네생활 댓글쓰기Ok
	 * @param session
	 * @param lifeCommentVO
	 * @return
	 */
	@PostMapping(value = "/writeComment")
	@ResponseBody
	public int writeLifeComment(HttpServletRequest request, @RequestBody DaangnLifeCommentVO lifeCommentVO) {
		DaangnMemberVO user = (DaangnMemberVO) request.getSession().getAttribute("user");
		lifeCommentVO.setUserRef(user.getIdx());
		String ipAddress = request.getRemoteAddr(); // 클라이언트의 IP 주소 가져오기
		lifeCommentVO.setIp(ipAddress);
		DaangnLifeBoardVO boardVO = lifeBoardService.selectByIdx(lifeCommentVO.getBoardRef());
		if(boardVO.getUserRef() == user.getIdx()) { // 게시글의 주인이 댓글을 작성하면
			lifeCommentVO.setIsOwner(1);
		}
		int result = lifeBoardService.insertLifeBoardComment(lifeCommentVO);
		return result;
	}
	/**
	 * 동네생활 댓글수정Ok
	 * @param lifeCommentVO
	 * @return
	 */
	@PostMapping(value = "/updateComment")
	@ResponseBody
	public int updateLifeComment(HttpServletRequest request, @RequestBody DaangnLifeCommentVO lifeCommentVO) {
		String ipAddress = request.getRemoteAddr(); // 클라이언트의 IP 주소 가져오기
		lifeCommentVO.setIp(ipAddress);
		int result = lifeBoardService.updateLifeBoardComment(lifeCommentVO);
		return result;
	}
	
	/**
	 * 동네생활 댓글삭제Ok
	 * @param commentRef
	 * @param boardRef
	 * @param parentComIdx
	 * @return
	 */
	@PostMapping(value = "/deleteComment")
	@ResponseBody
	public int deleteLifeComment(@RequestParam("commentRef") int commentRef, @RequestParam("boardRef") int boardRef, @RequestParam(value = "parentComIdx", required = false) Integer parentComIdx) {
		int result = lifeBoardService.deleteLifeBoardComment(commentRef, boardRef, parentComIdx);
		return result;
	}
	
	/**
	 * 동네생활 댓글 좋아요
	 * @param session
	 * @param commentRef
	 * @return
	 */
	@PostMapping(value = "/likeComment")
	@ResponseBody
	public int likeLifeComment(HttpSession session, @RequestParam("commentRef") int commentRef) {
		DaangnMemberVO user = (DaangnMemberVO) session.getAttribute("user");
	    int result = lifeBoardService.incrementCommentLikeCount(user.getIdx(), commentRef);
	    return result;
	}
	
	/**
	 * 동네생활 댓글 좋아요취소
	 * @param session
	 * @param commentRef
	 * @return
	 */
	@PostMapping(value = "/unlikeComment")
	@ResponseBody
	public int unlikeLifeComment(HttpSession session, @RequestParam("commentRef") int commentRef) {
		DaangnMemberVO user = (DaangnMemberVO) session.getAttribute("user");
	    int result = lifeBoardService.decrementCommentLikeCount(user.getIdx(), commentRef);
	    return result;
	}
}
