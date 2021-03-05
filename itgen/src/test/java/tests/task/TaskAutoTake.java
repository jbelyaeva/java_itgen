package tests.task;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.tasks.TaskData;
import data.model.tasks.Tasks;
import data.services.TaskService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TaskAutoTake extends TestBase {

  private final TaskService taskService = new TaskService();
  private TaskData taskClean = null;

  @BeforeMethod
  public void ensurePreconditions() {
    data.tasksAuto().set5_newAutoTask("task", "contactForPayment", "open", "21");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTaskAutoTake() {
    app.goTo().menuTasks();
    Tasks before = app.dbtasks().tasks();
    app.task().takeAutoTask();
    Tasks after = app.dbtasks().tasks();
    taskClean = app.dbtasks().lastTask();
    assertThat(after.size(), equalTo(before.size()));
    check(after);
    app.goTo().menuSchedule();
  }

  private void check(Tasks after) {
    data.tasksAuto().set1_newAutoTaskToday("task", "contactForPayment", "inProgress", "21");
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
    data.clean().taskAndSchedule();
  }
}
