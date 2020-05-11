package ru.stqa.pft.itgen.services;

import javafx.concurrent.Worker;
import ru.stqa.pft.itgen.dao.FamilyDao;
import ru.stqa.pft.itgen.dao.WorkerDao;
import ru.stqa.pft.itgen.model.FamilyData;
import ru.stqa.pft.itgen.model.WorkerData;

public class WorkerService {
  private WorkerDao workerDao = new WorkerDao();

  public WorkerService() {
  }

  public WorkerData findById(String id) {
    return workerDao.findById(id);
  }

  public void create(WorkerData worker) {
    workerDao.create(worker);
  }

  public void delete(WorkerData worker) {
    workerDao.delete(worker);
  }
}
