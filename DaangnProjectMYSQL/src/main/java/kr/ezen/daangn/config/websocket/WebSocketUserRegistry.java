package kr.ezen.daangn.config.websocket;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WebSocketUserRegistry {
    
    private Map< Integer, Set<Integer> > roomUserMap = new ConcurrentHashMap<>();

    public void addUser(Integer roomId, Integer userId) {
        roomUserMap.computeIfAbsent(roomId, k -> new HashSet<>()).add(userId);
        log.info("roomUserMap -> {}", roomUserMap);
    }

    public void removeUserFromRoom(Integer roomId, Integer userId) {
        Set<Integer> roomUsers = roomUserMap.get(roomId);
        if(roomUsers != null){
            roomUsers.remove(userId);
        }
        if (roomUsers.isEmpty()) {
            roomUserMap.remove(roomId);
        }
    }

    // 확인용
    public Map< Integer, Set<Integer> > getcheck(){
        return roomUserMap;
    }

    // 멀티채팅인 경우 아래 두 메서드를 수정하는 방향으로
    public Set<Integer> getRoomUsers(Integer roomId) {
        return roomUserMap.get(roomId);
    }
    /**  채팅방에 입장한 유저가 2명인지 확인 */
    public boolean hasUser(Integer roomId) {  //int roomId, Integer userId
        return roomUserMap.containsKey(roomId) && roomUserMap.get(roomId).size() == 2 ;
        // 일단 채팅방 유저가 2명이면 true를 리턴
    }

}
