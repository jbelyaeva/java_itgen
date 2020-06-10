package ru.stqa.pft.itgen.services;

import ru.stqa.pft.itgen.dao.RequestDao;
import ru.stqa.pft.itgen.dao.ScheduleDao;
import ru.stqa.pft.itgen.model.RequestData;
import ru.stqa.pft.itgen.model.ScheduleData;

public class RequestService {
  private RequestDao requestDao = new RequestDao();

  public RequestService() {
  }

  public RequestData findByIdAndDelete(String id) {
    return requestDao.findByIdAndDelete(id);
  }

  public void delete(RequestData request) {
    requestDao.delete(request);
  }

  public void save(RequestData request) {
    requestDao.save(request);
  }
}
