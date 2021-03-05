package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.users.TrainerData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;

public class TrainerDao implements Dao<TrainerData> {

  @Override
  public void save(TrainerData trainer) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(trainer);
  }

  @Override
  public TrainerData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(TrainerData.class).field("id").equal(id).first();
  }

  @Override
  public void drop() {

  }

  @Override
  public <E> void updateField(String idTrainer, String nameFiled, E data) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TrainerData> query = datastore.createQuery(TrainerData.class).filter("id", idTrainer);
    UpdateOperations ops = datastore.createUpdateOperations(TrainerData.class).set(nameFiled, data);
    datastore.update(query, (UpdateOperations<TrainerData>) ops);
  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TrainerData> query = datastore.createQuery(TrainerData.class).filter("id", id);
    UpdateOperations ops = datastore.createUpdateOperations(TrainerData.class).set(nameField, data);
    datastore.update(query, (UpdateOperations<TrainerData>) ops);
  }

  @Override
  public void delete(TrainerData trainerData) {

  }

  @Override
  public void deleteField(String idTrainer, String nameField) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TrainerData> query = datastore.createQuery(TrainerData.class)
        .field("id")
        .equal(idTrainer);
    datastore.update(query, datastore.createUpdateOperations(TrainerData.class).unset(nameField));
  }

  @Override
  public TrainerData deleteById(String id) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<TrainerData> query = datastore.createQuery(TrainerData.class).filter("id", id);
    TrainerData trainer = datastore.findAndDelete(query);
    return trainer;
  }
}
