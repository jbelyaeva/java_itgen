package ru.stqa.pft.itgen.services;

import ru.stqa.pft.itgen.dao.TrainerDao;
import ru.stqa.pft.itgen.dao.WorkerDao;
import ru.stqa.pft.itgen.model.TrainerData;
import ru.stqa.pft.itgen.model.WorkerData;

public class TrainerService {
  private TrainerDao trainerDao = new TrainerDao();

  public TrainerService() {
  }

  public TrainerData findById(String id) {
    return trainerDao.findById(id);
  }

  public void create(TrainerData trainer) {
    trainerDao.create(trainer);
  }

  public void delete(TrainerData trainer) {
    trainerDao.delete(trainer);
  }
}
