package data.services;

import data.dao.CandidatesDao;
import data.model.candidate.CandidateData;

public class CandidateService {

  private final CandidatesDao candidatesDao = new CandidatesDao();

  public CandidateService() {}

  public void save(CandidateData candidate) {
    candidatesDao.save(candidate);
  }

  public void drop() {
    candidatesDao.drop();
  }

  public CandidateData findById(String id) {
    return candidatesDao.findById(id);
  }
}
