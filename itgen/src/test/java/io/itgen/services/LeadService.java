package io.itgen.services;

import io.itgen.dao.LeadDao;
import io.itgen.model.LeadData;
import io.itgen.model.ScheduleData;
import io.itgen.model.StudentData;
import io.itgen.model.TaskData;

public class LeadService {
  private LeadDao leadDao = new LeadDao();

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

  public LeadData findById (String id) {
    return leadDao.findById(id);
  }
}
