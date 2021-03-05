package app.appmanager.dbHelpers;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.model.chat.ChatMessageData;
import data.model.chat.ChatMessages;
import data.model.chat.ChatRoomData;
import data.model.chat.ChatRooms;
import data.model.chat.ChatSubscriptionData;
import data.model.chat.ChatSubscriptions;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import java.util.List;

public class DbHelperChat {

  Datastore datastore = morphiaSessionFactoryUtil();

  public ChatMessages chatMessages() {
    Query<ChatMessageData> q = datastore.createQuery(ChatMessageData.class);
    List<ChatMessageData> chatMessages = q.find().toList();
    return new ChatMessages(chatMessages);
  }

  public ChatRooms chatRooms() {
    Query<ChatRoomData> q = datastore.createQuery(ChatRoomData.class);
    List<ChatRoomData> chatRooms = q.find().toList();
    return new ChatRooms(chatRooms);
  }

  public ChatSubscriptions chatSubscriptions() {
    Query<ChatSubscriptionData> q = datastore.createQuery(ChatSubscriptionData.class);
    List<ChatSubscriptionData> chatSubscriptions = q.find().toList();
    return new ChatSubscriptions(chatSubscriptions);
  }

  public ChatMessageData lastMessage() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ChatMessageData> q = datastore.createQuery(ChatMessageData.class);
    long count = q.count();
    List<ChatMessageData> student = q.find().toList();
    ChatMessageData lastMessage = student.get(Math.toIntExact(count - 1));
    return lastMessage;
  }
}
