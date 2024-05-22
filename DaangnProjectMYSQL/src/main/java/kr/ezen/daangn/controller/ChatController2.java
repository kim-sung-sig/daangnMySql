package kr.ezen.daangn.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ezen.daangn.service.DaangnMemberService;
import kr.ezen.daangn.service.DaangnUsedmarektChatService;
import kr.ezen.daangn.vo.DaangnMemberVO;
import kr.ezen.daangn.vo.DaangnUsedmarketChatMessageVO;
import kr.ezen.daangn.vo.DaangnUsedmarketChatRoomVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ChatController2 {
	@Autowired
    private SimpMessagingTemplate messagingTemplate;
	@Autowired
	private DaangnUsedmarektChatService chatService;
	@Autowired
	private DaangnMemberService daangnMemberService;
	

	@MessageMapping("/chat2/message")
    public void message(DaangnUsedmarketChatMessageVO message) {
		log.info("메시지 받았음!");
		DaangnMemberVO sender = daangnMemberService.selectByIdx(message.getSender());
		DaangnUsedmarketChatRoomVO chatRoomVO = chatService.selectChatRoomByIdx(message.getChatRoomRef());
		Integer receivedUserIdx = chatRoomVO.getUserRef() == message.getSender() ? chatRoomVO.getBoardUserRef() : message.getSender();
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
			messagingTemplate.convertAndSend("/sub/chat/alarm/" + receivedUserIdx, message); // 유저에게
		}
		// 메시지 보내기
        messagingTemplate.convertAndSend("/sub/chat2/room/" + message.getChatRoomRef(), message); // 채팅방에
    }
	
	@PutMapping("/chat2/read")
	@ResponseBody
	public void messageUpdateReadCount(@RequestBody DaangnUsedmarketChatMessageVO chatMessageVO) {
		log.info("messageUpdateReadCount실행 : {}", chatMessageVO);
		chatService.updateReadCount(chatMessageVO.getIdx());
	}
	
	@PutMapping("/chat2/readAll")
	@ResponseBody
	public void messageUpdateReadCountAll(@RequestBody DaangnUsedmarketChatMessageVO chatMessageVO) {
		log.info("messageUpdateReadCountAll 실행 : {}", chatMessageVO);
		chatService.updateReadCountAll(chatMessageVO.getChatRoomRef(), chatMessageVO.getSender());
	}
}
