package kr.ezen.daangn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpSession;
import kr.ezen.daangn.service.DaangnMemberService;
import kr.ezen.daangn.service.DaangnNoticesService;
import kr.ezen.daangn.vo.CommonVO;
import kr.ezen.daangn.vo.DaangnMemberVO;
import kr.ezen.daangn.vo.NoticesVO;
import kr.ezen.daangn.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@Configuration
public class HomeController {
	
	@Autowired
	private DaangnMemberService daangnMemberService;
	@Autowired
	private DaangnNoticesService noticesService;
	
	@GetMapping(value = { "/", "/main", "/index" })
	public String home(HttpSession session, Model model) {
		DaangnMemberVO user = (DaangnMemberVO) session.getAttribute("user");
		if(user != null) {
			if(!(Boolean) session.getAttribute("isLogin")) {
				daangnMemberService.updateLastLoginDate(user.getIdx());
				session.setAttribute("isLogin", true);
			}
			model.addAttribute("a", daangnMemberService.selectByIdx(user.getIdx()));
		}
		return "index";
	}
	
	//===========================================================================================
	// 공지사항 목록
	//===========================================================================================
	/** 공지사항 목록보기 페이지 */
	@GetMapping(value = "/notice")
	public String notice(Model model, @ModelAttribute CommonVO cv) {
	    cv.setS(10);
	    cv.setB(5);
	    PagingVO<NoticesVO> noticeList = noticesService.getPagingList(cv);
	    model.addAttribute("pv", noticeList);
	    List<NoticesVO> highLightList = noticesService.selectByHighlight();
	    model.addAttribute("highLightList", highLightList);
	    log.info("highLightList => {}", highLightList);
	    return "notices";
	}
	/** 공지사항 하나 보기 페이지 */
	@GetMapping(value = "/notice/detail/{idx}")
	public String noticeDetail(Model model, @PathVariable(value = "idx") int idx) {
		NoticesVO notice = noticesService.getNoticesByIdx(idx);
		model.addAttribute("notice", notice);
		log.info("notice => {}", notice);
		return "noticeDetail";
	}
	
	
	//===========================================================================================
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	/**
	 * 초기유저 초기화하는 주소
	 */
	@GetMapping("/dbinit") // 테스트시 초기화시키는 곳! 모든 admin 계정과 test계정을 초기화 시켜주는 곳
	public String dbInit() {
		String query = "update daangn_member "
					 + "set password = ?, role = ?, nickName = ?, email = ' ', emailOk = 1, stAddress = ' ', dtAddress = ' ' "
					 + "where username = ?";
		jdbcTemplate.update(query, passwordEncoder.encode("123456"), "ROLE_ADMIN", "admin", "admin");
		jdbcTemplate.update(query, passwordEncoder.encode("123456"), "ROLE_ADMIN", "master", "master");
		jdbcTemplate.update(query, passwordEncoder.encode("123456"), "ROLE_ADMIN", "webmaster", "webmaster");
		jdbcTemplate.update(query, passwordEncoder.encode("123456"), "ROLE_ADMIN", "root", "root");
		jdbcTemplate.update(query, passwordEncoder.encode("123456"), "ROLE_ADMIN", "dba", "dba");

		jdbcTemplate.update(query, passwordEncoder.encode("123456"), "ROLE_USER", "재미있는중고", "testuser1");
		jdbcTemplate.update(query, passwordEncoder.encode("123456"), "ROLE_USER", "중고보물찾기", "testuser2");
		jdbcTemplate.update(query, passwordEncoder.encode("123456"), "ROLE_USER", "Carrot매니아", "testuser3");
		jdbcTemplate.update(query, passwordEncoder.encode("123456"), "ROLE_USER", "devel당근", "testuser4");
		jdbcTemplate.update(query, passwordEncoder.encode("123456"), "ROLE_USER", "당근Hunter", "testuser5");
		jdbcTemplate.update(query, passwordEncoder.encode("123456"), "ROLE_USER", "중고보물찾기", "testuser6");
		jdbcTemplate.update(query, passwordEncoder.encode("123456"), "ROLE_USER", "믿음직한구매자", "testuser7");
		jdbcTemplate.update(query, passwordEncoder.encode("123456"), "ROLE_USER", "따뜻한이웃", "testuser8");
		jdbcTemplate.update(query, passwordEncoder.encode("123456"), "ROLE_USER", "당근종합프로", "testuser9");
		return "redirect:/member/logout";
	}
}
