package io.itgen.services;

import io.itgen.dao.LeadDao;
import io.itgen.model.LeadData;

public class LeadService {

  private final LeadDao leadDao = new LeadDao();

  public LeadService() {}

  public void create(LeadData lead) {
    leadDao.save(lead);
  }

  public void DeleteById(String id) {
    leadDao.findByIdAndDelete(id);
  }

  public LeadData DeleteById(LeadData lead) {
    return leadDao.findByIdAndDelete(lead);
  }

  public void save(LeadData lead) {
    leadDao.save(lead);
  }

  public LeadData findById(String id) {
    return leadDao.findById(id);
  }
}
