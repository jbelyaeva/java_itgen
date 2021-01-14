package data.services;

import data.dao.ScheduleDao;
import data.model.schedule.ScheduleData;

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

  public <E> void updateField(String idSchedule, String nameFiled, E data) {
    scheduleDao.updateField(idSchedule, nameFiled, data);
  }
}
