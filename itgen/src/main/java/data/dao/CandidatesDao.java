package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.candidate.CandidateData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

public class CandidatesDao implements Dao<CandidateData> {

  @Override
  public void save(CandidateData candidate) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(candidate);
  }

  @Override
  public <T> void updateField(String id, String nameField, T data) {

  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(CandidateData candidateData) {

  }

  @Override
  public void deleteField(String id, String nameField) {

  }

  @Override
  public CandidateData deleteById(String id) {
    return null;
  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<CandidateData> query = datastore.createQuery(CandidateData.class);
    datastore.delete(query);
  }

  @Override
  public CandidateData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(CandidateData.class).field("id").equal(id).first();
  }
}
