package kr.ezen.daangn.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ezen.daangn.vo.DaangnUsedmarketChatRoomVO;


@Mapper
public interface DaangnUsedmarketBoardChatRoomDAO {
	
	/**
	 * 마지막으로 활성화된 시간 수정
	 * @param roomIdx
	 */
    void updateLastUpdateDate(int roomIdx);
    
    /**
     * 채팅방 만들기
     * @param chatRoomVO
     */
    void createChatRoom(DaangnUsedmarketChatRoomVO chatRoomVO);
    
    /**
     * 이미 채팅방이 있는지 확인 (없으면 0, 있으면 idx)
     * @param userRef
     * @param boardRef
     * @return
     */
    int checkUniqueChatRoom(@Param("userRef") int userRef, @Param("boardRef") int boardRef);
    
    /**
     * 유저에 해당하는 채팅방 얻기 (활성화된 채팅방만)
     * @param userRef
     * @return
     */
    List<DaangnUsedmarketChatRoomVO> getChatRoomsByUserRef(int userRef);
    
    /**
     * 게시글에 해당하는 채팅방 얻기
     * @param boardRef
     * @return
     */
    List<DaangnUsedmarketChatRoomVO> getChatRoomByboardRef(int boardRef);
    
    /**
     * 채팅방 활성화(유저에서)
     * @param roomIdx
     */
    void activateChatRoom(@Param("roomIdx") int roomIdx);
    
    /**
     * 채팅방 비활성화(유저에서) userRef = deleted1 , boardUserRef = deleted2
     * @param roomIdx
     * @param deleted1 
     * @param deleted2
     */
    void deactivateChatRoom(@Param("roomIdx") int roomIdx, @Param("deleted1") Integer deleted1, @Param("deleted2") Integer deleted2);
    
    /**
     * 채팅방 완전 비활성화 하기
     */
    void deactivateChatRoomCompletely(int roomIdx) throws SQLException;
    
    /**
     * 채팅방 완전 삭제 하기
     * @param roomIdx
     */
    void deleteChatRoom(int roomIdx);
}
