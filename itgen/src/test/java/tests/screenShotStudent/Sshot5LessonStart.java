package tests.screenShotStudent;

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import data.services.CommentService;
import data.services.FinishedChildLessonService;
import data.services.FinishedLessonService;
import data.services.MaterialBranchService;
import data.services.MaterialChildsService;
import data.services.MaterialService;
import data.services.ScheduleService;
import data.services.StudentService;
import data.services.TaskService;
import data.services.TestResultsService;
import data.services.TestService;
import java.awt.AWTException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

public class Sshot5LessonStart extends TestBase {

  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  TaskService taskService = new TaskService();
  CommentService commentService = new CommentService();
  MaterialChildsService materialChildsService = new MaterialChildsService();
  MaterialBranchService materialBranchService = new MaterialBranchService();
  FinishedChildLessonService finishedChildLessonService = new FinishedChildLessonService();
  FinishedLessonService finishedLessonService = new FinishedLessonService();
  MaterialService materialService = new MaterialService();
  TestService testService = new TestService();
  TestResultsService testResultsService = new TestResultsService();

  @Test
  public void testSshot5LessonStart() throws AWTException, IOException {
    String name = "Student_LessonStart_RU_Chrome";
    app.student().goToLesson();
    app.student().deleteAlerts();

    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//div[@class='text-muted']"));

    String[] deleteElements = {
        "//div[@class='text-capitalize']",
    };
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

    if (diff.getDiffSize() > 250) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }

  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    app.student().btnCloseTutorial();
    app.student().logoutByStudent();
    scheduleService.DeleteById("ScheduleYesterday");
    materialBranchService.drop();
    materialChildsService.drop();
    materialService.drop();
    finishedChildLessonService.drop();
    finishedLessonService.drop();
    taskService.drop();
    commentService.drop();
    app.trStudent()
        .changeDefaultStudent(
            "21",
            "Ребенок",
            "Дефолтный",
            new String[]{"child"},
            "beginner",
            "BY",
            "Europe/Minsk",
            2,
            "ru",
            "ru",
            "ru",
            "1",
            "+9875645311",
            2,
            "noTrial",
            0,
            null,
            null,
            0);
    studentService.deleteField("21", "finishedLessonsCount");
    studentService.deleteField("21", "lastSubjs");
    studentService.deleteField("21", "usedSubjs");
    studentService.deleteField("21", "lessonCount");
    commentService.drop();
    testService.drop();
    testResultsService.drop();
    scheduleService.DeleteById("ScheduleStart");
  }
}
