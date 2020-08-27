package io.itgen.services;

import io.itgen.dao.WorkerDao;
import io.itgen.model.WorkerData;

public class WorkerService {
  private WorkerDao workerDao = new WorkerDao();

  public WorkerService() {}

  public WorkerData DeleteById(String id) {
    return workerDao.findByIdAndDelete(id);
  }

  public void create(WorkerData worker) {
    workerDao.save(worker);
  }
}
