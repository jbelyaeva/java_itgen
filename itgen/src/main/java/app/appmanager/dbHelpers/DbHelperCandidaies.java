package app.appmanager.dbHelpers;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import data.model.candidate.CandidateData;
import data.model.candidate.Candidates;
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
