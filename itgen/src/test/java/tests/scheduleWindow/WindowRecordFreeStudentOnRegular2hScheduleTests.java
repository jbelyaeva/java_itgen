package tests.scheduleWindow;
/*Кейс: Записать сразу на постоянное занятие нового бесплатника*/

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

public class WindowRecordFreeStudentOnRegular2hScheduleTests extends TestBase {

  String period = "18:00 - 20:00";
  String name = "Маша Машина";
  ScheduleService scheduleService = new ScheduleService();

  @BeforeMethod
  public void ensurePreconditions() {
    scheduleService.drop();
    data.newFamilyWithRegularLessons().set3_FamilyAndRegularLessonTomorrow(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testWindowRecordFreeStudentOnRegular2h() {
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.windowSchedule().recordStudentOn2hRegular(name); // имя ученика, id тренера
    Schedules after = app.dbschedules().schedules();

    assertThat(after.size(), equalTo(before.size()));
    check(before, after);
    app.check().findElement(By.xpath("//button[contains(@class,'cancel')]"));
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student().family();
  }

  private void check(Schedules before, Schedules after) {
    data.schedules().set45_TomorrowRegularLessonWithStudent(period, "14");
    ScheduleData scheduleAdd = scheduleService.findById("newSchedule");

    for (ScheduleData scheduleBefore : before) {
      if (scheduleBefore.getId().equals("newSchedule")) {
        assertThat(after, equalTo(before.without(scheduleBefore).withAdded(scheduleAdd)));
        return;
      }
    }
  }
}
