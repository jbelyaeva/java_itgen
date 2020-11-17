package data.services;

import data.dao.ChatMessageDao;
import data.model.chat.ChatMessageData;

public class ChatMessageService {

  private final ChatMessageDao chatMessageDao = new ChatMessageDao();

  public ChatMessageService() {}

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
