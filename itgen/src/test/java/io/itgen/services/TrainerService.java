package io.itgen.services;

import io.itgen.dao.TrainerDao;
import io.itgen.model.TrainerData;

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