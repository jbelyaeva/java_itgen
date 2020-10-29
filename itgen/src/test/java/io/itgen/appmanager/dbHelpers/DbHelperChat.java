package io.itgen.appmanager.dbHelpers;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.model.chat.ChatMessageData;
import io.itgen.model.chat.ChatMessages;
import io.itgen.model.chat.ChatRoomData;
import io.itgen.model.chat.ChatRooms;
import io.itgen.model.chat.ChatSubscriptionData;
import io.itgen.model.chat.ChatSubscriptions;
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

}