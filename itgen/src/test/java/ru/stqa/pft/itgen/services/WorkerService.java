package ru.stqa.pft.itgen.services;

import ru.stqa.pft.itgen.dao.WorkerDao;
import ru.stqa.pft.itgen.model.WorkerData;

public class WorkerService {
  private WorkerDao workerDao = new WorkerDao();

  public WorkerService() {
  }

  public WorkerData findByIdAndDelete(String id) {
    return workerDao.findByIdAndDelete(id);
  }

  public void create(WorkerData worker) {
    workerDao.save(worker);
  }

  //public void delete(WorkerData worker) {
  // workerDao.delete(worker);
  // }
}
