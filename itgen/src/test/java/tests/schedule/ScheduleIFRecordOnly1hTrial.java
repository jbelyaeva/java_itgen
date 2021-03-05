package tests.schedule;
/*Т-155
 * Перейти в  поп-ап Создать расписание.
 * Выбрать чек-бокс Индивидуальное
 * убедиться, что записать на пробное 2 ч нельзя
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ScheduleIFRecordOnly1hTrial extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testScheduleIFRecordOnly2hTrial() {
    app.goTo().menuSchedule();
    app.schedule().createSingleIFSchedule();
    app.schedule().chooseDefaultStudentFoRecordOnIF(app.dbschedules().lastStudent().getId());
    app.check().onDisabled(app.schedule().getTypeLessonTrialDefault());
    app.schedule().btnClosePopup();
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().student().taskAndSchedule().family().parent();
  }
}
