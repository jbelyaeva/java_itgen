package ru.stqa.pft.itgen.services;

import ru.stqa.pft.itgen.dao.TrainerDao;
import ru.stqa.pft.itgen.model.TrainerData;

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
