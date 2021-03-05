package data.precondition.transactionHelper;

import data.model.achievemets.AchievementsUsersData;
import data.services.AchievementsService;
import java.util.Date;

public class TrAchievementHelper {

  private final AchievementsService achievementsService = new AchievementsService();

  public void newAchievement(
      String idAchievement,
      String idUser,
      int type,
      boolean done,
      Date startAt,
      Date doneAt) {
    AchievementsUsersData achievement =
        new AchievementsUsersData()
            .withId(idAchievement)
            .withUserId(idUser)
            .withType(type)
            .withDone(done)
            .withStartAt(startAt)
            .withDoneAt(doneAt);
    achievementsService.save(achievement);
  }
}
