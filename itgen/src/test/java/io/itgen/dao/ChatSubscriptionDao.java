package io.itgen.dao;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.connection.MFSessionFactory;
import io.itgen.model.chat.ChatSubscriptionData;

public class ChatSubscriptionDao {

  public void save(ChatSubscriptionData chatSubscribtionData) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(chatSubscribtionData);
  }

  public ChatSubscriptionData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(ChatSubscriptionData.class).field("id").equal(id).first();
  }

  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ChatSubscriptionData> query = datastore.createQuery(ChatSubscriptionData.class);
    datastore.delete(query);
  }
}

