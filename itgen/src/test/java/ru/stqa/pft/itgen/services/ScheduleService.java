package ru.stqa.pft.itgen.services;

import ru.stqa.pft.itgen.dao.ScheduleDao;
import ru.stqa.pft.itgen.model.ScheduleData;

public class ScheduleService {
  private ScheduleDao scheduleDao = new ScheduleDao();

  public ScheduleService() {
  }

 /* public ScheduleData findById1(String id) {
    return scheduleDao.findById(id);
  }*/

  public ScheduleData findByIdAndDelete(String id) {
    return scheduleDao.findByIdAndDelete(id);
  }

  public void save(ScheduleData schedule) {
    scheduleDao.save(schedule);
  }
 }
