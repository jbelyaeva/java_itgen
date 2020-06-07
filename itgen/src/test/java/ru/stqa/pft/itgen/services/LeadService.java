package ru.stqa.pft.itgen.services;

import ru.stqa.pft.itgen.dao.LeadDao;
import ru.stqa.pft.itgen.model.LeadData;

public class LeadService {
  private LeadDao leadDao = new LeadDao();

  public LeadService() {
  }

  public void create(LeadData lead) {
    leadDao.save(lead);
  }

  public void findByIdAndDelete(String id) {
    leadDao.findByIdAndDelete(id);
  }
}
