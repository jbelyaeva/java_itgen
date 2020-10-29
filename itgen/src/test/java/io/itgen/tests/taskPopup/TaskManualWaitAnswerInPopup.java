package io.itgen.tests.taskPopup;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.general.RunTestAgain;
import io.itgen.model.tasks.TaskData;
import io.itgen.model.tasks.Tasks;
import io.itgen.services.TaskService;
import io.itgen.tests.TestBase;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TaskManualWaitAnswerInPopup extends TestBase {

  private final TaskService taskService = new TaskService();
  private TaskData taskClean = null;
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
        .newManualTask(
            "PopupWaitAnswerTask",
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
  public void testTaskManualWaitAnswerInPopup() throws InterruptedException {
    app.goTo().menuTasks();
    Tasks before = app.dbtasks().tasks();
    app.task().waitAnswerManualTaskInPopup();
    Tasks after = app.dbtasks().tasks();
    taskClean = app.dbtasks().lastTask();
    assertThat(after.size(), equalTo(before.size()));
    check(after);
    app.goTo().menuSchedule();
  }

  private void check(Tasks after) {
    app.trTask()
        .saveManualTask(
            "PopupWaitAnswerTask",
            "Записать на пробное",
            createAt,
            "wait",
            duoDateWithTime,
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
