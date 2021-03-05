package tests.taskPopup;

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

public class TaskAutoTakeOnControlInPopup extends TestBase {
  private TaskData taskClean = null;
  private final TaskService taskService = new TaskService();

  @BeforeMethod
  public void ensurePreconditions() {
    data.tasksAuto().set1_newAutoTaskToday("task", "contactForPayment", "inProgress", "21");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTaskAutoTakeOnControlInPopup() throws InterruptedException {
    app.goTo().menuTasks();
    Tasks before = app.dbtasks().tasks();
    app.task().takeOnControlAutoTaskInPopup();
    Tasks after = app.dbtasks().tasks();
    taskClean = app.dbtasks().lastTask();
    assertThat(after.size(), equalTo(before.size()));
    check(after);
    app.goTo().menuSchedule();
  }

  private void check(Tasks after) {
    data.tasksAuto().set4_AutoTaskTodayControl("contactForPayment", "inProgress", "21");
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
