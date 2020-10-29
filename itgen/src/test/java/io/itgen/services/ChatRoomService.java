package io.itgen.services;

import io.itgen.dao.ChatRoomDao;
import io.itgen.model.chat.ChatRoomData;

public class ChatRoomService {

  private final ChatRoomDao chatRoomDao = new ChatRoomDao();

  public ChatRoomService() {
  }

  public void save(ChatRoomData chatRoom) {
    chatRoomDao.save(chatRoom);
  }

  public ChatRoomData findById(String id) {
    return chatRoomDao.findById(id);
  }

  public void drop() {
    chatRoomDao.drop();
  }
}
