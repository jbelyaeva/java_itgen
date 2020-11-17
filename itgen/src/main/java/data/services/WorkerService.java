package data.services;

import data.dao.WorkerDao;
import data.model.users.WorkerData;

public class WorkerService {

  private final WorkerDao workerDao = new WorkerDao();

  public WorkerService() {}

  public WorkerData DeleteById(String id) {
    return workerDao.findByIdAndDelete(id);
  }

  public void save(WorkerData worker) {
    workerDao.save(worker);
  }

  public WorkerData findById(String id) {
    return workerDao.findById(id);
  }
}
