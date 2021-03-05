package tests.schedule;
/*Т-135
 * Создать попап расписание расписание. П
 * Убедиться, что в окне добавился чб Индивидуальное, чб не выбран по дефолту
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.Test;

public class ScheduleIFVisualPopupCreationTests extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testScheduleIFVisualPopupCreationTests() {
    app.goTo().menuSchedule();
    app.schedule().btnCreateSchedule();
    app.check().findElement(app.schedule().getCheckboxIF());
    app.check().notChecked(app.schedule().getCheckboxIF());
    app.goTo().menuTasks(); // что расписание только 1
  }
}
