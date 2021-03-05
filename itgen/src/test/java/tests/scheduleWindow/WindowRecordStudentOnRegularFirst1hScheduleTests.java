package tests.scheduleWindow;
/* автотест проверяет запись платника на постоянное занятие на первый час в постоянном расписании*/

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.schedule.ScheduleData;
import data.model.schedule.Schedules;
import data.services.ScheduleService;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WindowRecordStudentOnRegularFirst1hScheduleTests extends TestBase {

  String period = "18:00 - 20:00";
  String name = "Маша Машина";
  ScheduleService scheduleService = new ScheduleService();

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamilyWithRegularLessons().set3_FamilyAndRegularLessonTomorrow(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testWindowRecordStudentOnRegularFirst1h() {
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.windowSchedule().recordStudentOnRegularFirst1h(name, "14");
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    // проверка, что назначен новый тренер и остальные записи не изменились
    check(before, after);
    app.check().findElement(By.xpath("//button[contains(@class,'cancel')]"));
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student().family();
  }

  private void check(Schedules before, Schedules after) {
    data.schedules().set44_TomorrowRegularLessonWithStudentOnFirstH(period, "14");
    ScheduleData scheduleAdd = scheduleService.findById("recordStudentOnLesson");

    for (ScheduleData scheduleBefore : before) {
      if (scheduleBefore.getId().equals("recordStudentOnLesson")) {
        assertThat(after, equalTo(before.without(scheduleBefore).withAdded(scheduleAdd)));
        return;
      }
    }
  }
}
