package io.itgen.tests.screenShotPar;
/* Скриншот страницы семьи. База изначально должна быть пустая. Тест создает семью, делает снимок,
   сравнивает его с эталонным.
 */


import io.itgen.appmanager.ApplicationManager;
import io.itgen.general.TimeGeneral;
import io.itgen.model.ScheduleData;
import io.itgen.model.StudentData;
import io.itgen.model.TaskData;
import io.itgen.model.Tasks;
import io.itgen.model.schedule.*;
import io.itgen.model.users.Contacts;
import io.itgen.model.users.Status;
import io.itgen.services.ScheduleService;
import io.itgen.services.StudentService;
import io.itgen.services.TaskService;
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

public class SshotEntryOnTrial extends TestBase {
  String period = "18:00 - 20:00";

  // тестовая ситуация: есть дефолтная семья, к которой добавлен ученик
  // и разовое расписание на завтра в 18.00, на которое нужно записать добавленного ученика
  @BeforeMethod
  public void ensurePreconditions() {
    TimeGeneral time = new TimeGeneral();

    // разовое занятие без учеников
    ScheduleService scheduleService = new ScheduleService();
    app.trScheduleTomorrow()
        .SingleScheduleWithoutStudent(time, scheduleService, period, "LKOnTrail", "14");

    // студент, добавленный в дефолтную семь, без пробного
    StudentService studentService = new StudentService();
    app.trStudent()
        .StudentAddDefaultFamily(
            studentService, "LKOnTrail", "expert", "BL", "Europe/Minsk", 2, "ru", "ru");
  }

  @Test
  public void testEntryOnTrial() throws AWTException, IOException {
    app.lkParent().RecordOnTrail();

    String name = "Parent_EntryOnTrial_RU_Chrome";
    String[] locatorIgnor = {
            "//div[@class='lesson']//span[1]",
            "//div[@class='day']",
            "//div[contains(@id,'MeteorToys')]"
    };

    app.sshot().changeTopBar();

    ImageDiff diff = app.sshot().getImageDiff(ApplicationManager.properties.getProperty("expected")
            , ApplicationManager.properties.getProperty("actual")
            , ApplicationManager.properties.getProperty("markedImages")
            , name, locatorIgnor);
    app.lkParent().btnLogo();
    if (diff.getDiffSize() > 100) { //погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.findByIdAndDelete("LKOnTrail");

    StudentService studentService = new StudentService();
    studentService.findByIdAndDelete("LKOnTrail");

    Tasks tasks = app.dbschedules().tasksComposition("LKOnTrail");
    TaskService taskService = new TaskService();
    for (TaskData taskClean : tasks) {
      taskService.findByIdAndDelete(taskClean.getId());
    }
  }
}
