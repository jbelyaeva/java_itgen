package ru.stqa.pft.itgen.services;

import ru.stqa.pft.itgen.dao.ScheduleDao;
import ru.stqa.pft.itgen.model.ScheduleData;

public class ScheduleService {
  private ScheduleDao scheduleDao = new ScheduleDao();

  public ScheduleService() {
  }

  public ScheduleData findById(String id) {
    return scheduleDao.findById(id);
  }

  public void create(ScheduleData schedule) {
    scheduleDao.create(schedule);
  }

  public void delete(ScheduleData schedule) { scheduleDao.delete(schedule);
  }
}
