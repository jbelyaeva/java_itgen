package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import data.connection.MFSessionFactory;
import data.model.candidate.CandidateData;

public class CandidatesDao {

  public void save(CandidateData candidate) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(candidate);
  }

  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<CandidateData> query = datastore.createQuery(CandidateData.class);
    datastore.delete(query);
  }

  public CandidateData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(CandidateData.class).field("id").equal(id).first();
  }
}
