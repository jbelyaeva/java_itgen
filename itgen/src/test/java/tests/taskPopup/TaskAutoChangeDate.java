package tests.taskPopup;

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

public class TaskAutoChangeDate extends TestBase {

  private final TaskService taskService = new TaskService();
  private TaskData taskClean = null;
  private final Date createAt = new Date();
  private final long duoDateSort = new Date().getTime() + 86400000; // на завтра
  private final Date duoDateWithTime = new Date(duoDateSort);
  private Date[] dates = null;
  private final String[] texts = null;
  private final String[] clients = null;
  private final String[] commentaries = null;

  @BeforeMethod
  public void ensurePreconditions() {
    app.trTask()
        .saveAutoTask(
            "AutoTaskChangeAssignee",
            "contactForPayment",
            createAt,
            "inProgress",
            new Date(),
            new Date().getTime(),
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
  public void testTaskAutoChangeDate() throws InterruptedException {
    app.goTo().menuTasks();
    Tasks before = app.dbtasks().tasks();
    app.task().changeDateAutoTaskInPopup(); // на завтра
    Tasks after = app.dbtasks().tasks();
    taskClean = app.dbtasks().lastTask();
    assertThat(after.size(), equalTo(before.size()));
    check(after);
    app.goTo().menuSchedule();
  }

  private void check(Tasks after) {
    // автозадача перешла опять в стек (нет исполнителя и статус опять open)
    dates = new Date[] {createAt, duoDateWithTime};
    app.trTask()
        .saveAutoTask(
            "AutoTaskChangeAssignee",
            "contactForPayment",
            createAt,
            "open",
            duoDateWithTime,
            duoDateSort,
            null,
            "21",
            "666",
            "21.00 : 23.00",
            dates,
            texts,
            clients,
            commentaries,
            "takeAutoTask_changeDateAutoTask");

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
    taskService.DeleteById(taskClean.getId());
  }
}
