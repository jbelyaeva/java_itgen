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

public class TaskManualWaitAnswerInStack extends TestBase {

  private final TaskService taskService = new TaskService();
  private TaskData taskClean = null;

  @BeforeMethod
  public void ensurePreconditions() {
    data.tasksManual()
        .set6_1_ManualTaskCreatorAdminAssigneeSuperPr0("task", "Записать на пробное", "open", "21");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTaskManualWaitAnswerInStack() {
    app.goTo().menuTasks();
    Tasks before = app.dbtasks().tasks();
    app.task().waitAnswerInStack();
    Tasks after = app.dbtasks().tasks();
    taskClean = app.dbtasks().lastTask();
    assertThat(after.size(), equalTo(before.size()));
    check(after);
    app.goTo().menuSchedule();
  }

  private void check(Tasks after) {
    data.tasksManual()
        .set11_1_ManualTaskTodayWaitAnswerPr0("task", "Записать на пробное", "wait", "21");
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
