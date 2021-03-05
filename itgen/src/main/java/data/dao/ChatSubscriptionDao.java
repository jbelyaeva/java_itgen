package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.chat.ChatSubscriptionData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

public class ChatSubscriptionDao implements Dao<ChatSubscriptionData> {

  @Override
  public void save(ChatSubscriptionData chatSubscribtionData) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(chatSubscribtionData);
  }

  @Override
  public <T> void updateField(String id, String nameField, T data) {

  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(ChatSubscriptionData chatSubscriptionData) {

  }

  @Override
  public void deleteField(String id, String nameField) {

  }

  @Override
  public ChatSubscriptionData deleteById(String id) {
    return null;
  }

  @Override
  public ChatSubscriptionData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(ChatSubscriptionData.class).field("id").equal(id).first();
  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ChatSubscriptionData> query = datastore.createQuery(ChatSubscriptionData.class);
    datastore.delete(query);
  }
}
