package io.itgen.services;

import io.itgen.dao.ScheduleDao;
import io.itgen.model.schedule.ScheduleData;

public class ScheduleService {

  private final ScheduleDao scheduleDao = new ScheduleDao();

  public ScheduleService() {}

  public ScheduleData DeleteById(String id) {
    return scheduleDao.findByIdAndDelete(id);
  }

  public void delete(ScheduleData schedule) {
    scheduleDao.delete(schedule);
  }

  public void save(ScheduleData schedule) {
    scheduleDao.save(schedule);
  }

  public ScheduleData findById(String id) {
    return scheduleDao.findById(id);
  }

  public void drop() {
    scheduleDao.drop();
  }
}
