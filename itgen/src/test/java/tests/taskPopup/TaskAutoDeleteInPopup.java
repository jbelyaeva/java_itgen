package tests.taskPopup;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import core.general.RunTestAgain;
import data.model.tasks.Tasks;
import data.services.TaskService;
import app.testbase.TestBase;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TaskAutoDeleteInPopup extends TestBase {

  private final TaskService taskService = new TaskService();
  private final Date createAt = new Date();
  private final Date duoDateWithTime = new Date();
  private final long duoDateSort = new Date().getTime();
  private final Date[] dates = null;
  private final String[] texts = null;
  private final String[] clients = null;
  private final String[] commentaries = null;

  @BeforeMethod
  public void ensurePreconditions() {
    app.trTask()
        .saveAutoTask(
            "AutoTaskDeleteInPopup",
            "contactForPayment",
            createAt,
            "inProgress",
            duoDateWithTime,
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
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTaskAutoDeleteInPopup() throws InterruptedException {
    app.goTo().menuTasks();
    Thread.sleep(3000);
    Tasks before = app.dbtasks().tasks();
    app.task().deleteAutoTaskInPopup();
    Tasks after = app.dbtasks().tasks();
    assertThat(after.size(), equalTo(before.size() - 1));
    app.goTo().menuSchedule();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    if (taskService.findById("AutoTaskDeleteInPopup") != null) {
      taskService.DeleteById("AutoTaskDeleteInPopup");
    }
  }
}
