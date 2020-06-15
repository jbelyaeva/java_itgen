package io.itgen.services;

import io.itgen.dao.RequestDao;
import io.itgen.model.RequestData;

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
