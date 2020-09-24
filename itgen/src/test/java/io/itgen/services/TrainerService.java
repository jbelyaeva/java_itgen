package io.itgen.services;

import io.itgen.dao.TrainerDao;
import io.itgen.model.ParentData;
import io.itgen.model.TrainerData;

public class TrainerService {

  private final TrainerDao trainerDao = new TrainerDao();

  public TrainerService() {
  }

  public TrainerData DeleteById(String id) {
    return trainerDao.findByIdAndDelete(id);
  }

  public void save(TrainerData trainer) {
    trainerDao.save(trainer);
  }

  public TrainerData findById(String id) {
    return trainerDao.findById(id);
  }

}
