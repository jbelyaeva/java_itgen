package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.chat.ChatRoomData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

public class ChatRoomDao implements Dao<ChatRoomData> {

  @Override
  public void save(ChatRoomData chatRoomData) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(chatRoomData);
  }

  @Override
  public <T> void updateField(String id, String nameField, T data) {

  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(ChatRoomData chatRoomData) {

  }

  @Override
  public void deleteField(String id, String nameField) {

  }

  @Override
  public ChatRoomData deleteById(String id) {
    return null;
  }

  @Override
  public ChatRoomData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(ChatRoomData.class).field("id").equal(id).first();
  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ChatRoomData> query = datastore.createQuery(ChatRoomData.class);
    datastore.delete(query);
  }
}
