package io.itgen.appmanager.dbHelpers;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.model.CandidateData;
import io.itgen.model.Candidates;
import java.util.List;

public class DbHelperCandidaies {

  Datastore datastore = morphiaSessionFactoryUtil();

  public Candidates candidates() {
    Query<CandidateData> q = datastore.createQuery(CandidateData.class);
    List<CandidateData> candidates = q.find().toList();
    return new Candidates(candidates);
  }

  public CandidateData lastCandidate() {
    Query<CandidateData> q = datastore.createQuery(CandidateData.class);
    long count = q.count();
    List<CandidateData> candidate = q.find().toList();
    CandidateData lastCandidate = candidate.get(Math.toIntExact(count - 1));
    return lastCandidate;
  }

  public Candidates trySeveralTimeGetNewDateFromDB(Candidates before) {
    List<CandidateData> candidates = null;
    for (int i = 0; i < 5; i++) {
      Query<CandidateData> q = datastore.createQuery(CandidateData.class);
      candidates = q.find().toList();
      if (candidates.size() == before.size() + 1) {
        break;
      }
    }
    return new Candidates(candidates);
  }
}
