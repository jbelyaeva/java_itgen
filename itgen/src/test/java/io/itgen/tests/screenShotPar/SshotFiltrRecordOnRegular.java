package io.itgen.tests.screenShotPar;

import io.itgen.appmanager.ApplicationManager;
import io.itgen.general.TimeGeneral;
import io.itgen.model.*;
import io.itgen.model.schedule.*;
import io.itgen.model.users.Contacts;
import io.itgen.model.users.FinishedLessonsCountBySkill;
import io.itgen.model.users.Status;
import io.itgen.services.*;
import io.itgen.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class SshotFiltrRecordOnRegular extends TestBase {

  String period = "18:00 - 20:00";

  // тестовая ситуация: есть дефолтная семья, к которой добавлен ученик, прошедший вчера пробное в
  // 18.00
  @BeforeMethod
  public void ensurePreconditions() {
    TimeGeneral time = new TimeGeneral();
    ScheduleService scheduleService = new ScheduleService();

    // первое пробное занятие, которое вчера завершил ученик с Был
    app.trScheduleYesterday()
        .FinishingFirstTrialLesson(
            time,
            scheduleService,
            period,
            "FinishedSchedule",
            "14",
            "LkRecordOnRegularSchedule",
            "1");

    // студент, добавленный в дефолтную семью, который прошел пробное успешно
    StudentService studentService = new StudentService();
    app.trStudent()
        .StudentAddDefaultFamily_AfterTrial(
            studentService,
            "LkRecordOnRegularSchedule",
            "expert",
            "BL",
            "Europe/Minsk",
            2,
            "ru",
            "ru");
  }

  @Test
  public void testFiltrRecordOnRegular() throws AWTException, IOException {
    app.lkParent().GoToFiltrRecordRegular();

    String name = "Parent_FiltrRecordOnRegular_RU_Chrome";
    String[] locatorIgnor = {
      "//p[@class='user']",
      "//span[@class='selected-icon']",
      "//div[@class='DayPickerInput']//input",
      "//div[contains(@id,'MeteorToys')]"
    };

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor);
    if (diff.getDiffSize() > 100) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }

    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.findByIdAndDelete("FinishedSchedule");

    FamilyService familyService = new FamilyService();
    FamilyData family = new FamilyData().withId("111").withTrialBonusOff(false).withTierId("txa");
    familyService.save(family);

    StudentService studentService = new StudentService();
    studentService.findByIdAndDelete("LkRecordOnRegularSchedule");

    Tasks tasks = app.dbschedules().tasksComposition("LkRecordOnRegularSchedule");
    TaskService taskService = new TaskService();
    for (TaskData taskClean : tasks) {
      taskService.findByIdAndDelete(taskClean.getId());
    }
  }
}
