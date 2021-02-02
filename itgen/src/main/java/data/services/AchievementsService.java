package data.services;

import data.dao.AchievementsDao;
import data.model.achievemets.AchievementsUsersData;

public class AchievementsService {

  private final AchievementsDao achievementDao = new AchievementsDao();

  public AchievementsService() {
  }

  public AchievementsUsersData DeleteById(String id) {
    return achievementDao.findByIdAndDelete(id);
  }

  public void create(AchievementsUsersData achievement) {
    achievementDao.save(achievement);
  }

  public void save(AchievementsUsersData achievement) {
    achievementDao.save(achievement);
  }

  public AchievementsUsersData findById(String id) {
    return achievementDao.findById(id);
  }

  public void drop() {
    achievementDao.drop();
  }
}
