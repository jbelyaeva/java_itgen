package tests.taskPopup;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.tasks.Tasks;
import data.services.TaskService;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TaskManualDeleteInPopup extends TestBase {

  TaskService taskService = new TaskService();
  Date createAt = new Date();
  Date duoDateWithTime = new Date();
  long duoDateSort = new Date().getTime();

  @BeforeMethod
  public void ensurePreconditions() {
    app.trTask()
        .newManualTask(
            "PopupDeleteTask",
            "777",
            "666",
            "Записать на пробное",
            1,
            createAt,
            "open",
            duoDateWithTime,
            duoDateSort,
            "21");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTaskManualDeleteInPopup() throws InterruptedException {
    app.goTo().menuTasks();
    Tasks before = app.dbtasks().tasks();
    app.task().deleteTaskInPopup();
    Tasks after = app.dbtasks().tasks();
    assertThat(after.size(), equalTo(before.size() - 1));
    app.goTo().menuSchedule();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    taskService.drop();
  }
}
