package tests.task;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import core.general.RunTestAgain;
import data.model.tasks.TaskData;
import data.model.tasks.Tasks;
import data.services.TaskService;
import app.testbase.TestBase;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TaskAutoGetControlInStack extends TestBase {

  private final TaskService taskService = new TaskService();
  private final Date createAt = new Date();
  private final Date duoDateWithTime = new Date();
  private final long duoDateSort = new Date().getTime();
  private final Date[] dates = null;
  private final String[] texts = null;
  private final String[] clients = null;
  private final String[] commentaries = null;
  private TaskData taskClean = null;

  @BeforeMethod
  public void ensurePreconditions() {
    app.trTask()
        .saveAutoTask(
            "AutoTaskTakeOnControl",
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
  public void testTaskAutoGetControlInStack() {
    app.goTo().menuTasks();
    Tasks before = app.dbtasks().tasks();
    app.task().takeOnControlAutoTaskInStack();
    taskClean = app.dbtasks().lastTask();
    Tasks after = app.dbtasks().tasks();
    assertThat(after.size(), equalTo(before.size()));
    check(after);
    app.goTo().menuSchedule();
  }

  private void check(Tasks after) {
    app.trTask()
        .saveAutoTask(
            "AutoTaskTakeOnControl",
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
            "takeAutoTask_takeOnControlAutoTask");

    TaskData taskAdd = taskService.findById(taskClean.getId());

    for (TaskData taskAfter : after) {
      if (taskAfter.getId().equals(taskClean.getId())) {
        assertThat(after, equalTo(after.without(taskAfter).withAdded(taskAdd)));
        return;
      }
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    taskService.drop();
  }
}
