package io.itgen.tests.screenShot;

import static io.itgen.appmanager.ApplicationManager.properties;

import io.itgen.services.FamilyService;
import io.itgen.services.StudentService;
import io.itgen.services.TaskService;
import io.itgen.tests.TestBase;
import java.awt.AWTException;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

public class SshotMainTasks extends TestBase {
  private final TaskService taskService = new TaskService();
  private final StudentService studentService = new StudentService();
  private final FamilyService familyService = new FamilyService();
  private final Date createAt = new Date();
  private final Date duoDateWithTimeFirst = new Date();
  private final Date duoDateWithTimeSecond = new Date(new Date().getTime() - 86400000);
  private final long duoDateSort = new Date().getTime();
  private final Date[] dates = null;
  private final String[] texts = null;
  private final String[] clients = null;
  private final String[] commentaries = null;

  @BeforeMethod
  public void ensurePreconditions() {
    app.trFamily().newFamily("Student", false, "txc");

    app.trStudent()
        .newStudent(
            "Student",
            "Маша",
            "Машина",
            "expert",
            "AL",
            "Europe/Minsk",
            2,
            "ru",
            "ru",
            "Student");

    app.trTask()
        .newManualTask(
            "FiltrTaskFirst",
            "666",
            "777",
            "Узнать почту у родителя",
            1,
            new Date(),
            "open",
            new Date(),
            new Date().getTime(),
            "21");
    app.trTask()
        .newAutoTask(
            "FiltrTaskAutoFirst",
            "contactForPayment",
            createAt,
            "open",
            duoDateWithTimeFirst,
            duoDateSort,
            "21",
            "21.00 : 23.00");
    taskService.deleteField("FiltrTaskAutoFirst", "priority");
    app.trTask()
        .newManualTask(
            "FiltrTaskSecond",
            "666",
            "666",
            "Узнать почту у родителя",
            1,
            new Date(),
            "open",
            new Date(),
            new Date().getTime(),
            "Student");

    app.trTask()
        .newManualTask(
            "FiltrTaskThird",
            "666",
            "14",  //Настя Бокша - тренер
            "Проверить материалы",
            1,
            new Date(),
            "open",
            new Date(),
            new Date().getTime(),
            "Student");

    app.trTask()
        .saveAutoTask(
            "FiltrTaskAutoSecond",
            "contactForPayment",
            createAt,
            "inProgress",
            duoDateWithTimeSecond,
            duoDateSort,
            "666",
            "21",
            "21",
            "21.00 : 23.00",
            dates,
            texts,
            clients,
            commentaries,
            "newAutoTask_takeAutoTask");
    taskService.deleteField("FiltrTaskAutoSecond", "priority");
    app.trTask()
        .saveManualTask(
            "FilterDone",
            "Записать на пробное",
            createAt,
            "closed",
            duoDateWithTimeSecond,
            duoDateSort,
            "666",
            "21",
            "777",
            "666",
            0,
            dates,
            texts,
            clients,
            commentaries,
            "newTask_doneTask");

    app.trTask()
        .saveManualTask(
            "FiltrWait",
            "Записать на пробное",
            createAt,
            "wait",
            duoDateWithTimeFirst,
            duoDateSort,
            "666",
            "21",
            "777",
            "666",
            1,
            dates,
            texts,
            clients,
            commentaries,
            "newTask_waitAnswer");

  }

  @Test
  public void testSshotMainTasks() throws AWTException, IOException {
    String name = "Admin_TasksMain_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//td[@class='dueDate']"));

    app.goTo().menuRequests();
    app.goTo().menuTasks();
    app.sshot().changeTopBar();

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                properties.getProperty("expected"),
                properties.getProperty("actual"),
                properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.25f);
    if (diff.getDiffSize() > 100) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    taskService.drop();
    studentService.DeleteById("Student");
    familyService.DeleteById("Student");
  }
}
