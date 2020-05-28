package ru.stqa.pft.itgen.services;

import ru.stqa.pft.itgen.dao.LeadDao;
import ru.stqa.pft.itgen.dao.StudentDao;
import ru.stqa.pft.itgen.model.LeadData;
import ru.stqa.pft.itgen.model.StudentData;

public class LeadService {
  private LeadDao leadDao = new LeadDao();

  public LeadService() {
  }

  public LeadData findById(String id) {
    return leadDao.findById(id);
  }

  public void create(LeadData lead) {
    leadDao.save(lead);
  }

  public void findByIdAndDelete(String id) {
    leadDao.findByIdAndDelete(id);
  }
}
