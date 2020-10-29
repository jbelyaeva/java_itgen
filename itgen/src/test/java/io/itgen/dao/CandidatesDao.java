package io.itgen.dao;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.connection.MFSessionFactory;
import io.itgen.model.CandidateData;
import io.itgen.model.tasks.TaskData;

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
