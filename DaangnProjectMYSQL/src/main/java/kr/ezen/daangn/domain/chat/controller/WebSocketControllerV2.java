package kr.ezen.daangn.domain.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import kr.ezen.daangn.service.DaangnMemberService;
import kr.ezen.daangn.service.DaangnUsedmarektChatService;
import kr.ezen.daangn.vo.DaangnUsedmarketChatMessageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WebSocketControllerV2 {

    private final SimpMessagingTemplate messagingTemplate;
    private final DaangnUsedmarektChatService chatService;
    private final DaangnMemberService daangnMemberService;

    /**
     * 채팅방 메시지를 받는 주소
     * @param message
     */
    @MessageMapping("/chat/message")
    public void message(DaangnUsedmarketChatMessageVO message){

    }

    /**
     * 채팅방이거나 채팅방이 아닐때 알람을 받을 주소
     * @param str
     */
    @MessageMapping("/alarm/message")
    public void alarmMessage(String str){
        
    }

}
