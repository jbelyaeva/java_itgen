package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import data.connection.MFSessionFactory;
import data.model.users.TrainerData;

public class TrainerDao {

  public void save(TrainerData trainer) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(trainer);
  }

  public TrainerData findByIdAndDelete(String id) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<TrainerData> query = datastore.createQuery(TrainerData.class).filter("id", id);
    TrainerData trainer = datastore.findAndDelete(query);
    return trainer;
  }

  public TrainerData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(TrainerData.class).field("id").equal(id).first();
  }
}