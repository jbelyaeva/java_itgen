package tests.lkParent;
/* T-70 */
/*
 * В дефолтную семью добавлен ученик с завершенным вчера занятием c тренером Настей.
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
