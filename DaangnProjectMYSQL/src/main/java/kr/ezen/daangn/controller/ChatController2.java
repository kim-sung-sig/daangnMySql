package kr.ezen.daangn.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ezen.daangn.config.websocket.WebSocketUserRegistry;
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
	@Autowired
	private WebSocketUserRegistry userRegistry;

	@MessageMapping("/chat2/message")
    public void message(DaangnUsedmarketChatMessageVO message, SimpMessageHeaderAccessor headerAccessor) {
		log.info("메시지 받았음!");
		DaangnMemberVO sender = daangnMemberService.selectByIdx(message.getSender());
		if(sender == null){
			return ;
		}

		message.setNickName(sender.getNickName());
		if(sender.getUserFile() != null) {
			message.setUserProfile(sender.getUserFile().getSaveFileName());
		}
		DaangnUsedmarketChatRoomVO chatRoomVO = chatService.selectChatRoomByIdx(message.getChatRoomRef());

		// 상대 유저 번호 구하기
		boolean isBoardOwner = message.getSender() == chatRoomVO.getBoardUserRef(); // 보낸이가 게시글의 주인인지 확인
		Integer receivedUserIdx = isBoardOwner ? message.getSender() : chatRoomVO.getBoardUserRef();
		log.info("message : {}", message);
		
		// 채팅방 입장시 메시지를 받아서 저장..
		if(message.getTypeRef() == 1){
			log.info("입장메시지 였음");
			Integer senderIdx = message.getSender();
			Integer roomId = message.getChatRoomRef();
			headerAccessor.getSessionAttributes().put("userId", message.getSender());
			headerAccessor.getSessionAttributes().put("roomId", message.getChatRoomRef());
			userRegistry.addUser(roomId, senderIdx);

			log.info("RoomUserMapByRoomId => {}", userRegistry.getRoomUsers(roomId));
			return ;
		}

		// 메시지 진짜 메시지인 경우
		if(message.getTypeRef() == 2 || message.getTypeRef() == 4) { // TALK 또는 RESERVE
			boolean isIn = userRegistry.hasUser(message.getChatRoomRef()); // 입장객이 두명인경우!
			if(isIn) { // 채팅방에 들어와 있음
				message.setReaded(0);
			} else { // 채팅방에 들어와 있지 않음
				message.setReaded(1);
			}
			chatService.insertMessage(message); // 메시지 저장
			message.setCreateDate(LocalDateTime.now()); // 시간을 클라이언트측에서 받을 수도 있음
			messagingTemplate.convertAndSend("/sub/chat/alarm/" + receivedUserIdx, message); // 유저에게 알림 전송 (채팅방에 들어와 있지 않은 경우)
		}
		// 메시지 보내기
        messagingTemplate.convertAndSend("/sub/chat2/room/" + message.getChatRoomRef(), message); // 채팅방에 메시지 전송
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
