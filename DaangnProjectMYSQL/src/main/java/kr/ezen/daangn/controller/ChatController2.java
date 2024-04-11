package kr.ezen.daangn.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import kr.ezen.daangn.service.DaangnMemberService;
import kr.ezen.daangn.service.DaangnUsedmarektChatService;
import kr.ezen.daangn.service.DaangnUsedmarketService;
import kr.ezen.daangn.vo.DaangnMemberVO;
import kr.ezen.daangn.vo.DaangnUsedmarketChatMessageVO;
import kr.ezen.daangn.vo.DaangnUsedmarketChatRoomVO;
import kr.ezen.daangn.vo.ScrollVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/chat2")
public class ChatController2 {
	@Autowired
    private SimpMessagingTemplate messagingTemplate;
	@Autowired
	private DaangnUsedmarektChatService chatService;
	@Autowired
	private DaangnUsedmarketService boardService;
	@Autowired
	private DaangnMemberService daangnMemberService;
		
	/** 채팅방 목록 보기 */
	@GetMapping("/rooms")
	public String chatRooms(HttpSession session, Model model) {
		if(session.getAttribute("user") == null) { // 로그인을 하지 않은 경우
			return "redirect:/";
		}
		var user = (DaangnMemberVO) session.getAttribute("user");
		log.info("채팅방 보기 실행");
		List<DaangnUsedmarketChatRoomVO> chatRoomList = chatService.getChatRoomsByUserRef(user.getIdx());
		log.info("채팅방 보기 결과 {}개 => {}", chatRoomList.size(), chatRoomList);
		// 유저의 채팅방을 찾아 model에 넘겨준다
		model.addAttribute("chatRoomList", chatRoomList);
		return "usedmarket/chatRooms";
	}
	
	// 채팅방 입장하기
	@GetMapping("/room/{chatRoomIdx}")
	public String chatRoom(HttpSession session, Model model, @PathVariable(value = "chatRoomIdx") int chatRoomIdx) {
		if(session.getAttribute("user") == null) { // 로그인을 하지 않은 경우
			return "redirect:/";
		}
		var user = (DaangnMemberVO) session.getAttribute("user");
		DaangnUsedmarketChatRoomVO chatRoomVO = chatService.selectChatRoomByIdx(chatRoomIdx);
		log.info("chatRoomVO => {}", chatRoomVO);
		// 또한 chatRoom이 가지는 유저의 idx가 아닌경우
		if(user.getIdx() != chatRoomVO.getUserRef() && user.getIdx() != chatRoomVO.getBoardUserRef()) { // 채팅의 주인이 아닌경우
			return "redirect:/";
		}
		
		chatService.updateReadCountAll(chatRoomIdx, user.getIdx());
		model.addAttribute("chatRoom", chatRoomVO);
		model.addAttribute("chatRoomIdx", chatRoomIdx);
		model.addAttribute("sender", user.getIdx());
		model.addAttribute("lastItemIdx", chatService.getChatMessageLastIdx() + 1);
		return "usedmarket/chatRoom";
	}
	
	@PostMapping("/findChatMessages")
	@ResponseBody
	public List<DaangnUsedmarketChatMessageVO> findChatMessages(@RequestBody ScrollVO sv, HttpSession session){
		if(session.getAttribute("user") == null) { // 로그인을 하지 않은 경우
			return null;
		}
		log.info("findChatMessages 실행 {}", sv);
		var user = (DaangnMemberVO) session.getAttribute("user");
		DaangnUsedmarketChatRoomVO chatRoomVO = chatService.selectChatRoomByIdx(sv.getChatRoomIdx());
		List<DaangnUsedmarketChatMessageVO> list = null;
		if(user.getIdx() == chatRoomVO.getUserRef()) {
			list = chatService.getMessagesByChatRoomRef(sv.getChatRoomIdx(), sv.getLastItemIdx(), sv.getSizeOfPage(), 0, null);			
		} else {
			list = chatService.getMessagesByChatRoomRef(sv.getChatRoomIdx(), sv.getLastItemIdx(), sv.getSizeOfPage(), null, 0);						
		}
		return list;
	}
	
	// 채팅방 조회 및 생성
	@GetMapping("/createChatRoom")
	public String creathChatRoomGet(HttpSession session) {
		return "redirect:/";
	}
	@PostMapping("/createChatRoom")
	@ResponseBody
	public String creathChatRoomPost(HttpSession session, @RequestBody DaangnUsedmarketChatRoomVO chatRoomVO) {
		DaangnMemberVO memberVO = (DaangnMemberVO) session.getAttribute("user");
		if(memberVO == null) {
			return "0";
		}
		chatRoomVO.setUserRef(memberVO.getIdx());
		chatRoomVO.setBoardUserRef(boardService.selectByIdx(chatRoomVO.getBoardRef()).getUserRef());
		log.info("chatRoom :{}", chatRoomVO);
		// 1. 체팅방이 있는지 확인한다. (없으면 만들고 숫자를 넘기고 그 주소로가자, 있으면 숫자를 넘기고 그 주소로 가자)
		int result = chatService.createChatRoom(chatRoomVO.getUserRef(), chatRoomVO.getBoardRef(), chatRoomVO.getBoardUserRef());
		return result + "";
	}
	
	
	@MessageMapping("/message")
    public void message(DaangnUsedmarketChatMessageVO message) {
		DaangnMemberVO sender = daangnMemberService.selectByIdx(message.getSender());
		if(sender != null) {
			message.setNickName(sender.getNickName());			
			if(sender.getUserFile() != null) {
				message.setUserProfile(sender.getUserFile().getSaveFileName());			
			}
		}
		log.info("message : {}", message);
		// 메시지 저장
		if(message.getTypeRef() == 2 || message.getTypeRef() == 4) { // TALK 또는 RESERVE
			chatService.insertMessage(message);
			message.setCreateDate(LocalDateTime.now());
		}
		// 메시지 보내기
        messagingTemplate.convertAndSend("/sub/chat2/room/" + message.getChatRoomRef(), message);
    }
	
	@PutMapping("/chat/read")
	@ResponseBody
	public void messageUpdateReadCount(@RequestBody DaangnUsedmarketChatMessageVO chatMessageVO) {
		log.info("messageUpdateReadCount실행 : {}", chatMessageVO);
		chatService.updateReadCount(chatMessageVO.getIdx());
	}
	
	@PutMapping("/chat/readAll")
	@ResponseBody
	public void messageUpdateReadCountAll(@RequestBody DaangnUsedmarketChatMessageVO chatMessageVO) {
		log.info("messageUpdateReadCountAll 실행 : {}", chatMessageVO);
		chatService.updateReadCountAll(chatMessageVO.getChatRoomRef(), chatMessageVO.getSender());
	}
	
	
	// TODO 삭제하기 만들기
}
