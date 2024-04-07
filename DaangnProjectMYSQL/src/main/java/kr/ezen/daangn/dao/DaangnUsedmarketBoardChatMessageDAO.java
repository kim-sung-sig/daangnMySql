package kr.ezen.daangn.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ezen.daangn.vo.DaangnUsedmarketChatMessageVO;

@Mapper
public interface DaangnUsedmarketBoardChatMessageDAO {
	
	/**
	 * 채팅 메시지 얻기
	 * @param lastItemIdx
	 * @param sizeOfPage
	 * @param chatRoomRef
	 * @param deleted1
	 * @param deleted2
	 * @return
	 * @throws SQLException
	 */
	List<DaangnUsedmarketChatMessageVO> selectPagedChatMessageByChatRoomRef(
    		@Param("lastItemIdx") int lastItemIdx,
    		@Param("sizeOfPage") int sizeOfPage,
    		@Param("chatRoomRef") int chatRoomRef,
    		@Param("deleted1") Integer deleted1,
    		@Param("deleted2") Integer deleted2
    	) throws SQLException;
	
	/**
	 * 최대 idx 얻기
	 * @return
	 * @throws SQLException
	 */
    int getLastIdx() throws SQLException;
    
    /**
     * 메시지 저장하기
     * @param chatMessageVO
     * @throws SQLException
     */
    void insertChat(DaangnUsedmarketChatMessageVO chatMessageVO) throws SQLException;
    
    /**
     * 읽음 처리하기
     * @param idx
     * @throws SQLException
     */
    void updateChat(int idx) throws SQLException;
    
    /**
     * 입장시 모두 읽음 처리하기
     * @param map
     * @throws SQLException
     */
    void updateAllChat(@Param("chatRoomRef") int chatRoomRef, @Param("sender") int sender) throws SQLException;

    int unreadCount(@Param("chatRoomRef") int chatRoomRef, @Param("sender") int sender) throws SQLException;

    void deactivateChatMessage(@Param("chatRoomRef") int chatRoomRef, @Param("deleted1") Integer deleted1, @Param("deleted2") Integer deleted2) throws SQLException;
}
