package data.services;

import data.dao.TrainerDao;
import data.model.users.TrainerData;

public class TrainerService {

  private final TrainerDao trainerDao = new TrainerDao();

  public TrainerService() {}

  public TrainerData DeleteById(String id) {
    return trainerDao.findByIdAndDelete(id);
  }

  public void save(TrainerData trainer) {
    trainerDao.save(trainer);
  }

  public TrainerData findById(String id) {
    return trainerDao.findById(id);
  }

  public <E> void updateField(String idTrainer, String nameFiled, E[] data) {
    trainerDao.updateField(idTrainer, nameFiled, data);
  }
}
