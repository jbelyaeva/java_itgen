package data.precondition;

import static app.appmanager.HelperBase.DateWithCorrectionDays;

public class Achievements extends TransactionManager {

  protected static final DataManager data = new DataManager();

  /**
   * пополнен баланс админом у дефолтной семьи
   */
  public void set1() {
    trAchievement().newAchievement(
        "21_1",
        "21",
        0,
        true,
        DateWithCorrectionDays(-2),
        DateWithCorrectionDays(-1));
  }

  public void set2() {
    trAchievement().newAchievement(
        "21_0",
        "21",
        0,
        true,
        DateWithCorrectionDays(-2),
        DateWithCorrectionDays(-1));

    trAchievement().newAchievement(
        "21_1",
        "21",
        1,
        true,
        DateWithCorrectionDays(-2),
        DateWithCorrectionDays(-1));
  }
}
