package kr.ezen.daangn.config.websocket;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 소캣 생성 소멸 감지 리스너
@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListener {

    // private final Map<Integer, Set<String>> chatSessions = new ConcurrentHashMap<>();
    private final WebSocketUserRegistry userRegistry;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        log.info("WebSocket connection opened");
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String webSocketSessionId = headerAccessor.getSessionId();
        log.info("Received a new web socket connection, sessionId: {}", webSocketSessionId);
        /*
        log.info("WebSocket connection opened");
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        log.info("{},", event.getMessage());
        
        String webSocketSessionId = headerAccessor.getSessionId();
        Integer httpSessionId = (Integer) headerAccessor.getSessionAttributes().get("sessionUserIdx");
        Integer roomId = (Integer) headerAccessor.getSessionAttributes().get("roomId");
        // String roomIdStr = headerAccessor.getFirstNativeHeader("roomId");
        log.info("roomId => {}", roomId);
        if (roomId == null || httpSessionId == null) {
            log.error("error");
            return;
        }

        log.info("Received a new web socket connection, sessionId: {}", webSocketSessionId);
        log.info("httpSessionId => {}", httpSessionId);
        log.info("Room ID => {}", roomId);

        String targetValue = webSocketSessionId + "_" + httpSessionId;
        Set<String> roomSessions = chatSessions.computeIfAbsent(roomId, key -> ConcurrentHashMap.newKeySet());
        roomSessions.add(targetValue);
        chatSessions.put(roomId, roomSessions);
         */
    }

    // 웹소캣이 끊김을 감지
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        log.info("WebSocket connection closed");
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String sessionId = headerAccessor.getSessionId();
        log.info("Web socket connection closed, sessionId: {}", sessionId);

        Integer roomId = (Integer) headerAccessor.getSessionAttributes().get("roomId");
        Integer userId = (Integer) headerAccessor.getSessionAttributes().get("userId");
        
        if(userId != null && roomId != null){
            userRegistry.removeUserFromRoom(roomId, userId);
            log.info("userId => {}", userId);
            log.info("roomId => {}", roomId);

            if(userRegistry.getRoomUsers(roomId) != null && userRegistry.getRoomUsers(roomId).contains(userId)) {
                userRegistry.getRoomUsers(roomId).remove(userId);
            }
        }
        log.info("roomUserMap -> {}", userRegistry.getcheck());
    }

    /*
    // 멀티 채팅인 경우 여기로직을 수정하여 처리한다! boolean
    public boolean isInChatRoomCount(Integer roomId) {
        boolean result = false;
        Set<String> chatRoom = chatSessions.get(roomId);
        log.info("chatRoom => {}", chatRoom);
        if(chatRoom == null) {
            return result;
        }
        return chatRoom.size() == 2; // 여기를 contains로 처리하면 될듯
    }
     */
}
