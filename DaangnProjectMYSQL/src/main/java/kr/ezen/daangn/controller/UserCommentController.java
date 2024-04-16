package kr.ezen.daangn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.ezen.daangn.service.DaangnUserCommentService;
import kr.ezen.daangn.vo.DaangnCommentVO;
import kr.ezen.daangn.vo.DaangnMemberVO;
import kr.ezen.daangn.vo.ScrollVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 유저 평을 조회 저장 수정 삭제하는 컨트롤러
 */
@Slf4j
@RestController
public class UserCommentController {
	
	@Autowired
	private DaangnUserCommentService commentService;
	
	// 유저 평 조회
	@GetMapping("/user/comments")
	public List<DaangnCommentVO> getUserComments(@ModelAttribute ScrollVO sv){
		log.info("getUserComments 호출 lastItemIdx=>{}, sizeOfPage=>{}, userRef=>{}", sv.getLastItemIdx(), sv.getSizeOfPage(), sv.getUserRef());
		List<DaangnCommentVO> list = commentService.getUserComments(sv);
		log.info("getUserComments 리턴 {}", list);
		return list;
	}
	
	// 유저 평 1개 조회
	@GetMapping("/user/comments/{idx}")
	public DaangnCommentVO getUserCommentByIdx(@PathVariable("idx") int idx) {
		log.info("getUserCommentByIdx 호출 idx=>{}", idx);
		DaangnCommentVO comment = commentService.getUserCommentByIdx(idx);
		log.info("getUserCommentByIdx 리턴 {}", comment);
		return comment;
	}
	
	// 유저 평 저장
	@PostMapping("/user/comments")
	public int insertUserComment(HttpServletRequest request, @RequestBody DaangnCommentVO comment) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {
			return 0;
		}
		var user = (DaangnMemberVO) session.getAttribute("user");
		comment.setIp(request.getRemoteAddr());
		comment.setWriterRef(user.getIdx());
		
		log.info("insertUserComment 호출 {}", comment);
		int result = commentService.insertUserComment(comment);
		log.info("insertUserComment 리턴 result => {}", result);
		return result;
	}
	
	// 유저 평 수정
	@PutMapping("/user/comments/{idx}")
	public int updateUserComment(HttpSession session, @PathVariable("idx") int idx, @RequestBody DaangnCommentVO comment) {
		if(session.getAttribute("user") == null) { // 로그인하지 않으면 불가
			return 0;
		}
		var user = (DaangnMemberVO) session.getAttribute("user");
		DaangnCommentVO dbComment = commentService.getUserCommentByIdx(idx);
		if(dbComment.getWriterRef() != user.getIdx()) { // 작성자가 아니면 불가
			return 0;
		}
		
		log.info("updateUserComment 호출 idx=>{}, {}", idx, comment);
		int result = commentService.updateComment(comment);
		log.info("updateUserComment 리턴 result => {}", result);
		return result;
	}
	
	// 유저 평 삭제
	@DeleteMapping("/user/comments/{idx}")
	public int deleteUserComment(HttpSession session, @PathVariable("idx") int idx) {
		if(session.getAttribute("user") == null) { // 로그인하지 않으면 불가
			return 0;
		}
		var user = (DaangnMemberVO) session.getAttribute("user");
		DaangnCommentVO dbComment = commentService.getUserCommentByIdx(idx);
		if(dbComment.getWriterRef() != user.getIdx()) { // 작성자가 아니면 불가
			return 0;
		}
		
		log.info("deleteUserComment 호출 idx=>{}", idx);
		int result = commentService.deleteComment(idx);
		log.info("deleteUserComment 리턴 result => {}", result);
		return result;
	}
}
