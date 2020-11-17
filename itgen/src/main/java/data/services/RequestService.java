package data.services;

import data.dao.RequestDao;
import data.model.requests.RequestData;

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
