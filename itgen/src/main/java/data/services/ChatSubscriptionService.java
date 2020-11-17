package data.services;

import data.dao.ChatSubscriptionDao;
import data.model.chat.ChatSubscriptionData;

public class ChatSubscriptionService {

  private final ChatSubscriptionDao chatSubscriptionDao = new ChatSubscriptionDao();

  public ChatSubscriptionService() {}

  public void save(ChatSubscriptionData chatSubscribtion) {
    chatSubscriptionDao.save(chatSubscribtion);
  }

  public ChatSubscriptionData findById(String id) {
    return chatSubscriptionDao.findById(id);
  }

  public void drop() {
    chatSubscriptionDao.drop();
  }
}
