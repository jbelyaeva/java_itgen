package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.achievemets.AchievementsUsersData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

public class AchievementsDao {

  public void save(AchievementsUsersData achievement) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(achievement);
  }

  public AchievementsUsersData findByIdAndDelete(String id) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<AchievementsUsersData> query = datastore.createQuery(AchievementsUsersData.class)
        .filter("id", id);
    AchievementsUsersData achievement = datastore.findAndDelete(query);
    return achievement;
  }

  public AchievementsUsersData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(AchievementsUsersData.class).field("id").equal(id).first();
  }

  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<AchievementsUsersData> query = datastore.createQuery(AchievementsUsersData.class);
    datastore.delete(query);
  }
}
