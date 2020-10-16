package io.itgen.tests.task;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.model.tasks.TaskData;
import io.itgen.model.tasks.Tasks;
import io.itgen.services.TaskService;
import io.itgen.tests.TestBase;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TaskManualGetControlInStack extends TestBase {

  private final TaskService taskService = new TaskService();
  private final Date createAt = new Date();
  private final long duoDateSort = new Date().getTime() + 86400000;
  private final Date duoDateWithTime = new Date(duoDateSort);
  private final String[] texts = null;
  private final String[] clients = null;
  private final String[] commentaries = null;
  private TaskData taskClean = null;
  private Date[] dates = null;

  @BeforeMethod
  public void ensurePreconditions() {
    app.trTask()
        .newManualTask(
            "OnControlTaskInStack",
            "777",
            "666",
            "Записать на пробное",
            0,
            new Date(),
            "open",
            new Date(),
            new Date().getTime(),
            "21");
  }

  @Test
  public void testTaskManualGetControlInStack() {
    app.goTo().menuTasks();
    Tasks before = app.dbtasks().tasks();
    app.task().takeOnControManualTaskInStack();
    Tasks after = app.dbtasks().tasks();
    taskClean = app.dbtasks().lastTask();
    assertThat(after.size(), equalTo(before.size()));
    check(after);
    app.goTo().menuSchedule();
  }

  private void check(Tasks after) {
    dates = new Date[]{createAt, duoDateWithTime};
    app.trTask()
        .saveManualTask(
            "OnControlTaskInStack",
            "Записать на пробное",
            createAt,
            "open",
            duoDateWithTime,
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
            "newTask_takeOnControlTask");

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
