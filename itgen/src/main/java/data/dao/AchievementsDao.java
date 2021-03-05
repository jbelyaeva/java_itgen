package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.achievemets.AchievementsUsersData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

public class AchievementsDao implements Dao<AchievementsUsersData> {

  @Override
  public void save(AchievementsUsersData achievement) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(achievement);
  }

  @Override
  public <T> void updateField(String id, String nameField, T data) {

  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(AchievementsUsersData achievementsUsersData) {

  }

  @Override
  public void deleteField(String id, String nameField) {

  }

  @Override
  public AchievementsUsersData deleteById(String id) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<AchievementsUsersData> query = datastore.createQuery(AchievementsUsersData.class)
        .filter("id", id);
    AchievementsUsersData achievement = datastore.findAndDelete(query);
    return achievement;
  }

  @Override
  public AchievementsUsersData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(AchievementsUsersData.class).field("id").equal(id).first();
  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<AchievementsUsersData> query = datastore.createQuery(AchievementsUsersData.class);
    datastore.delete(query);
  }
}
