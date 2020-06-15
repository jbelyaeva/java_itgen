package io.itgen.services;

import io.itgen.dao.ScheduleDao;
import io.itgen.model.ScheduleData;

public class ScheduleService {
  private ScheduleDao scheduleDao = new ScheduleDao();

  public ScheduleService() {
  }

  public ScheduleData findByIdAndDelete(String id) {
    return scheduleDao.findByIdAndDelete(id);
  }

  public void delete(ScheduleData schedule) {
    scheduleDao.delete(schedule);
  }

  public void save(ScheduleData schedule) {
    scheduleDao.save(schedule);
  }
}
