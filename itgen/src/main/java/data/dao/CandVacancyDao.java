package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.candidate.CandidateVacanciesData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

public class CandVacancyDao implements Dao<CandidateVacanciesData> {

  public void save(CandidateVacanciesData candidateV) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(candidateV);
  }

  @Override
  public <T> void updateField(String id, String nameField, T data) {

  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(CandidateVacanciesData candidateVacanciesData) {

  }

  public void deleteField(String idVacancy, String nameField) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<CandidateVacanciesData> query = datastore.createQuery(CandidateVacanciesData.class)
        .field("id")
        .equal(idVacancy);
    datastore.update(query,
        datastore.createUpdateOperations(CandidateVacanciesData.class).unset(nameField));
  }

  @Override
  public CandidateVacanciesData deleteById(String id) {
    return null;
  }

  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<CandidateVacanciesData> query = datastore.createQuery(CandidateVacanciesData.class);
    datastore.delete(query);
  }

  public CandidateVacanciesData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(CandidateVacanciesData.class).field("id").equal(id).first();
  }
}
