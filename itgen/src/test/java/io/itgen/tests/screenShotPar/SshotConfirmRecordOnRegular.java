package io.itgen.tests.screenShotPar;
/* Скриншот страницы семьи. База изначально должна быть пустая. Тест создает семью, делает снимок,
  сравнивает его с эталонным.
*/

import io.itgen.appmanager.ApplicationManager;
import io.itgen.general.TimeGeneral;
import io.itgen.model.TaskData;
import io.itgen.model.Tasks;
import io.itgen.services.ScheduleService;
import io.itgen.services.StudentService;
import io.itgen.services.TaskService;
import io.itgen.tests.TestBase;
import java.awt.AWTException;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

public class SshotConfirmRecordOnRegular extends TestBase {
  String period = "18:00 - 20:00";

  // тестовая ситуация: есть дефолтная семья, к которой добавлен ученик, прошедший вчера пробное в
  // 18.00 и постоянное расписание на завтра в 18.00, на которое нужно записать ученика
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

    // занятие завтра, на которое нужно записать ученика
    app.trScheduleTomorrow()
        .RegularScheduleWithoutStudents(
            time, scheduleService, period, "LkRecordOnRegularSchedule", "14");

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
  public void testConfirmRecordOnRegular() throws AWTException, IOException {
    app.lkParent().confirmRecordOnRegular();

    String name = "Parent_ConfirmRecordOnRegular_RU_Chrome";
    String[] locatorIgnor = {
      "//p[@class='user']",
      "//div[@class='DayPickerInput']//input",
      "//span[@class='selected-icon']",
      "//div[contains(@id,'MeteorToys')]",
      "//strong",
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
    app.lkParent().btnRecord();
    app.lkParent().btnLogo();

    if (diff.getDiffSize() > 100) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.findByIdAndDelete("FinishedSchedule");
    scheduleService.findByIdAndDelete("LkRecordOnRegularSchedule");

    StudentService studentService = new StudentService();
    studentService.findByIdAndDelete("LkRecordOnRegularSchedule");

    Tasks tasks = app.dbschedules().tasksComposition("LkRecordOnRegularSchedule");
    TaskService taskService = new TaskService();
    for (TaskData taskClean : tasks) {
      taskService.findByIdAndDelete(taskClean.getId());
    }
  }
}
