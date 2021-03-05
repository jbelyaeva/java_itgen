package data.services;

import data.dao.RequestDao;
import data.model.requests.RequestData;

public class RequestService {

  private final RequestDao requestDao = new RequestDao();

  public RequestService() {}

  public RequestData DeleteById(String id) {
    return requestDao.deleteById(id);
  }

  public void delete(RequestData request) {
    requestDao.delete(request);
  }

  public void save(RequestData request) {
    requestDao.save(request);
  }

  public void drop() {
    requestDao.drop();
  }

  public void deleteField(String idRequest, String nameField) {
    requestDao.deleteField(idRequest, nameField);
  }
}


