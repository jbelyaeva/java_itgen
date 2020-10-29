package io.itgen.services;

import io.itgen.dao.RequestDao;
import io.itgen.model.requests.RequestData;

public class RequestService {

  private final RequestDao requestDao = new RequestDao();

  public RequestService() {}

  public RequestData DeleteById(String id) {
    return requestDao.findByIdAndDelete(id);
  }

  public void delete(RequestData request) {
    requestDao.delete(request);
  }

  public void save(RequestData request) {
    requestDao.save(request);
  }
}
