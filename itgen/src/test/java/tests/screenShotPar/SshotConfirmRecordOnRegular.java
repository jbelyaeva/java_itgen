package tests.screenShotPar;
/* Скриншот страницы семьи. База изначально должна быть пустая. Тест создает семью, делает снимок,
  сравнивает его с эталонным.
*/

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import data.model.tasks.TaskData;
import data.model.tasks.Tasks;
import data.services.ScheduleService;
import data.services.StudentService;
import data.services.TaskService;
import java.awt.AWTException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

public class SshotConfirmRecordOnRegular extends TestBase {
  TaskService taskService = new TaskService();
  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  String period = "18:00 - 20:00";

  // тестовая ситуация: есть дефолтная семья, к которой добавлен ученик, прошедший вчера пробное в
  // 18.00 и постоянное расписание на завтра в 18.00, на которое нужно записать ученика
  @BeforeMethod
  public void ensurePreconditions() {
    app.trScheduleYesterday()
        .FinishingFirstTrialLesson(
            period, "FinishedSchedule", "14", "LkRecordOnRegularSchedule", "1");

    app.trStudent()
        .studentAddDefaultFamilyAfterLesson(
            "LkRecordOnRegularSchedule",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "Europe/Minsk",
            2,
            app.base().DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2,
            1,
            "trialFinished",
            "1",
            "1",
            1);

    app.trScheduleTomorrow()
        .RegularScheduleWithoutStudents(period, "LkRecordOnRegularSchedule", "14");
  }

  @Test
  public void testConfirmRecordOnRegular() throws AWTException, IOException {
    app.lkParent().confirmRecordOnRegular();

    String name = "Parent_ConfirmRecordOnRegular_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//p[@class='user']"));
    locatorIgnor.add(By.xpath("//div[@class='DayPickerInput']//input"));
    locatorIgnor.add(By.xpath("//span[@class='selected-icon']"));
    locatorIgnor.add(By.xpath("//div[contains(@id,'MeteorToys')]"));
    locatorIgnor.add(By.xpath("//div[@class='permanent']"));

    String[] deleteElements = {"//strong"};

    app.sshot().deleteElements(deleteElements);

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.25f);
    app.lkParent().btnRecord();
    app.lkParent().btnLogo();

    if (diff.getDiffSize() > 200) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    scheduleService.DeleteById("FinishedSchedule");
    scheduleService.DeleteById("LkRecordOnRegularSchedule");
    studentService.DeleteById("LkRecordOnRegularSchedule");

    Tasks tasks = app.dbschedules().tasksComposition("LkRecordOnRegularSchedule");
    for (TaskData taskClean : tasks) {
      taskService.DeleteById(taskClean.getId());
    }
  }
}
