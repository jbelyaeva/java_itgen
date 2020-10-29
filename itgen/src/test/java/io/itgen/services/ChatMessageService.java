package io.itgen.services;

import io.itgen.dao.ChatMessageDao;
import io.itgen.dao.MaterialDao;
import io.itgen.model.chat.ChatMessageData;
import io.itgen.model.materials.MaterialData;

public class ChatMessageService {

  private final ChatMessageDao chatMessageDao = new ChatMessageDao();

  public ChatMessageService() {
  }

  public void save(ChatMessageData chatMessage) {
    chatMessageDao.save(chatMessage);
  }

  public ChatMessageData findById(String id) {
    return chatMessageDao.findById(id);
  }

  public void drop() {
    chatMessageDao.drop();
  }
}
