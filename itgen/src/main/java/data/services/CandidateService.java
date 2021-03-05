package data.services;

import data.dao.CandVacancyDao;
import data.dao.CandidatesDao;
import data.model.candidate.CandidateData;
import data.model.candidate.CandidateVacanciesData;

public class CandidateService {

  private final CandidatesDao candidatesDao = new CandidatesDao();
  private final CandVacancyDao candVacancyDao = new CandVacancyDao();

  public CandidateService() {
  }

  public void save(CandidateData candidate) {
    candidatesDao.save(candidate);
  }

  public void saveVacancies(CandidateVacanciesData candidateV) {
    candVacancyDao.save(candidateV);
  }

  public void deleteVacancyField(String idVacancy, String nameField) {
    candVacancyDao.deleteField(idVacancy, nameField);
  }

  public void drop() {
    candidatesDao.drop();
  }

  public void dropCandVacancy() {
    candVacancyDao.drop();
  }

  public CandidateData findById(String id) {
    return candidatesDao.findById(id);
  }
}
