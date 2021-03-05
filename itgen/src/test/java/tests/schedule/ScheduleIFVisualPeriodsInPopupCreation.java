package tests.schedule;
/*Т-136
 * Перейти в  поп-ап Создать расписание.
 * Выбрать чек-бокс Индивидуальное
 * Убедиться, что в выпадающем списке только часовые периоды
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.Test;

public class ScheduleIFVisualPeriodsInPopupCreation extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testScheduleIFVisualPeriodsInPopupCreation() {
    app.goTo().menuSchedule();
    app.schedule().btnCreateSchedule();
    app.schedule().selectCheckboxIF();
    app.schedule().dropdownTime();
    app.schedule().checkPeriods();
    app.goTo().menuTasks();
  }
}
