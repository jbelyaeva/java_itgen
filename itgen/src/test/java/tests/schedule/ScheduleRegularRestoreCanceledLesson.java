package tests.schedule;
/*T-374
 *Есть постоянное расписание на сегодня c учеником ( новая семья).
 * Отменить первое занятие / нажать кнопку Многоточие/ Восстановить
 * Проверить: что на этом занятие нет ученика - на остальных запись есть
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.Schedules;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScheduleRegularRestoreCanceledLesson extends TestBase {

  private final String period = "21:00 - 23:00";

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamilyWithRegularLessons().set2_FamilyWithRegularLesson(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testScheduleRegularRestoreCanceledLesson() {
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().cancel("newSchedule");
    app.schedule().restore();
    Schedules after = app.dbschedules().schedules();

    app.check().equalityOfTwoElements(after.size(), before.size());
    data.schedules().set17_TodayRegularScheduleWithoutStudentOnFirstLesson(period);
    Schedules afterNew = app.dbschedules().schedules();
    app.check().equalityOfTwoElements(after, afterNew);
    app.check().textElement(app.schedule().getLabelCancelSchedule(), "Не начато");
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student().parent().family();
  }
}
