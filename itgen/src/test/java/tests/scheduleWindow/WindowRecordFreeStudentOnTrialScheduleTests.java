package tests.scheduleWindow;
// автотест проверяет запись бесплатника на пробное занятие в постоянном расписании
// проверяет, что переключатель Пробное стоит по дефолту
// начальные данные: период, id тренера

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

public class WindowRecordFreeStudentOnTrialScheduleTests extends TestBase {

  String period = "18:00 - 20:00";
  String name = "Маша Машина";
  ScheduleService scheduleService = new ScheduleService();

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamily().set4_FamilyWithFreeStudentAndParent();
    data.schedules().set2_RegularScheduleWithoutStudents(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testWindowRecordFreeStudentOnTrial() {
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.windowSchedule().recordStudentOnTrial(name, "14"); // имя ученика, id тренера
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
    data.schedules().set47_TomorrowTrialLessonWithStudentOnRegular(period, "14");
    ScheduleData scheduleAdd = scheduleService.findById("newSchedule");

    for (ScheduleData scheduleBefore : before) {
      if (scheduleBefore.getId().equals("newSchedule")) {
        assertThat(after, equalTo(before.without(scheduleBefore).withAdded(scheduleAdd)));
        return;
      }
    }
  }
}
