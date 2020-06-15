package io.itgen.services;

import io.itgen.dao.LeadDao;
import io.itgen.model.LeadData;

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
