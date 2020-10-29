package io.itgen.dao;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.connection.MFSessionFactory;
import io.itgen.model.chat.ChatMessageData;
import io.itgen.model.materials.MaterialBranchData;

public class ChatMessageDao {

  public void save(ChatMessageData chatMessageData) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(chatMessageData);
  }

  public ChatMessageData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(ChatMessageData.class).field("id").equal(id).first();
  }

  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ChatMessageData> query = datastore.createQuery(ChatMessageData.class);
    datastore.delete(query);
  }
}

