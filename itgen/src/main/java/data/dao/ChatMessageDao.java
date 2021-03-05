package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.chat.ChatMessageData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

public class ChatMessageDao implements Dao<ChatMessageData> {

  @Override
  public void save(ChatMessageData chatMessageData) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(chatMessageData);
  }

  @Override
  public <T> void updateField(String id, String nameField, T data) {

  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(ChatMessageData chatMessageData) {

  }

  @Override
  public void deleteField(String id, String nameField) {

  }

  @Override
  public ChatMessageData deleteById(String id) {
    return null;
  }

  @Override
  public ChatMessageData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(ChatMessageData.class).field("id").equal(id).first();
  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ChatMessageData> query = datastore.createQuery(ChatMessageData.class);
    datastore.delete(query);
  }
}
