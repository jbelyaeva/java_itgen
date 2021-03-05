package tests.lkParent;
/* T-71 */
/*
 * В дефолтную семью добавлен ученик с завершенным вчера занятием c тренером Настей (с был и не был)
 * Перейти в запись на постоянку и убедиться, что тренер Настя есть в списке тренеров под чертой.
 * Перейти в запись на разовое и убедиться, что тренер Настя есть в списке тренеров под чертой.
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerInListWhenLessonsFinishedWithWasAndNotWas extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
   data.defFamily().set16_YesterdayTwoLessonsWasAndAbort_StudentAddInDefaultFamily();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTrainerInListWhenLessonsFinishedWithWasAndNotWas() {
    app.lkParentRecord().trainerInListOnRegularRecord();
    app.check().findElement(app.lkParentRecord().getTrainerNastyaInList());
    app.lkParentRecord().trainerInListOnSingleRecord();
    app.check().findElement(app.lkParentRecord().getTrainerNastyaInList());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student();
  }
}
