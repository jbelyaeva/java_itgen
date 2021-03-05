package tests.scheduleWindow;

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

public class WindowRecordStudentOnSingle2hScheduleTests extends TestBase {

  String period = "18:00 - 20:00";
  String name = "Маша Машина";
  ScheduleService scheduleService = new ScheduleService();

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamilyWithSingleLessons().set6_FamilyAndTomorrowSingleLesson(period);
   /* app.trScheduleTomorrow().SingleScheduleWithoutStudent(period, "recordOnSchedule", "14");
    app.trFamily().newFamily("recordStudent", false, "txc");

    app.trStudent()
        .newStudent(
            "recordStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "recordStudent",
            "Europe/Minsk",
            2,
            app.base().DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            new String[]{"1"},
            2,
            "noTrial");*/
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testWindowRecordStudentOnSingle2h() {
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.windowSchedule().recordStudentOn2hSingle(name); // имя ученика для поиска, id тренера
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    check(before, after);
    app.check().findElement(By.xpath("//button[contains(@class,'cancel')]"));
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().student().family().parent().taskAndSchedule();
  }

  private void check(Schedules before, Schedules after) {
    data.schedules().set7_1_SingleScheduleWithOneStudentRecordOnSingleOnScratch(period);
    ScheduleData scheduleAdd = scheduleService.findById("recordOnSchedule");
    for (ScheduleData scheduleBefore : before) {
      if (scheduleBefore.getId().equals("recordOnSchedule")) {
        assertThat(after, equalTo(before.without(scheduleBefore).withAdded(scheduleAdd)));
        return;
      }
    }
  }
}
