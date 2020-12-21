package tests.lkParent;
/* T-70 */
/*
 * В дефолтную семью добавлен ученик с завершенным вчера занятием c тренером Настей.
 * Перейти в запись на постоянку и убедиться, что тренер Настя есть в списке тренеров под чертой.
 * Перейти в запись на разовое и убедиться, что тренер Настя есть в списке тренеров под чертой.
 */

import app.testbase.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerInListWhenRecordOnPaidAndLessonWithWas extends TestBase {
  private final String period = "18:00 - 20:00";

  @BeforeMethod
  public void ensurePreconditions() {
  data.defFamily().set12_LessonYesterdayFinished_StudentAddInDefaultFamily(period);
  }

  @Test
  public void testTrainerInListWhenRecordOnPaidAndLessonWithWas() {
    app.lkParent().trainerInListOnRegularRecord();
    app.check().findElement(app.lkParent().getTrainerNastyaInList());
    app.lkParent().trainerInListOnSingleRecord();
    app.check().findElement(app.lkParent().getTrainerNastyaInList());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule().student();
  }
}
