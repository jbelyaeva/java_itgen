package ru.stqa.pft.itgen.dao;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import ru.stqa.pft.itgen.model.TrainerData;

import static ru.stqa.pft.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

public class TrainerDao {

  public void save(TrainerData trainer) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.save(trainer);
  }

  public TrainerData findByIdAndDelete(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TrainerData> query = datastore.createQuery(TrainerData.class).filter("id", id);
    TrainerData trainer = datastore.findAndDelete(query);
    return trainer;
  }
}

