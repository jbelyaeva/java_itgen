package io.itgen.services;

import io.itgen.dao.CandidatesDao;
import io.itgen.dao.TestDao;
import io.itgen.model.CandidateData;
import io.itgen.model.tasks.TaskData;
import io.itgen.model.typeform.TestData;

public class CandidateService {

  private final CandidatesDao candidatesDao = new CandidatesDao();

  public CandidateService() {
  }

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
