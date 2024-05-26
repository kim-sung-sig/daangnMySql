package kr.ezen.daangn.config.websocket;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import jakarta.servlet.http.HttpSession;
import kr.ezen.daangn.vo.DaangnMemberVO;

public class HttpSessionHandshakeInterceptor implements HandshakeInterceptor {
    /*
     * HandshakeInterceptor는 WebSocket 연결의 핸드셰이크 단계에서 동작합니다.
     * ChannelInterceptor는 WebSocket의 메시지 전송 단계에서 동작합니다.
     */

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes
    ) throws Exception {
        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
        HttpSession httpSession = servletRequest.getServletRequest().getSession();
        DaangnMemberVO loginUser = (DaangnMemberVO) httpSession.getAttribute("user");
        if(loginUser != null){
            attributes.put("sessionUserIdx", loginUser.getIdx());
        }
        String roomId = servletRequest.getServletRequest().getParameter("roomId");
        System.out.println(roomId);
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        // Do nothing
    }
}
