package ru.stqa.pft.itgen.services;

import ru.stqa.pft.itgen.dao.TrainerDao;
import ru.stqa.pft.itgen.dao.WorkerDao;
import ru.stqa.pft.itgen.model.TrainerData;
import ru.stqa.pft.itgen.model.WorkerData;

public class TrainerService {
  private TrainerDao trainerDao = new TrainerDao();

  public TrainerService() {
  }

  public TrainerData findByIdAndDelete(String id) {
    return trainerDao.findByIdAndDelete(id);
  }

  public void save(TrainerData trainer) {
    trainerDao.save(trainer);
  }

}
