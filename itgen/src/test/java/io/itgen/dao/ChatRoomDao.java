package io.itgen.dao;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.connection.MFSessionFactory;
import io.itgen.model.chat.ChatMessageData;
import io.itgen.model.chat.ChatRoomData;

public class ChatRoomDao {

  public void save(ChatRoomData chatRoomData) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(chatRoomData);
  }

  public ChatRoomData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(ChatRoomData.class).field("id").equal(id).first();
  }

  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ChatRoomData> query = datastore.createQuery(ChatRoomData.class);
    datastore.delete(query);
  }
}

