package io.itgen.tests.task;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.general.RunTestAgain;
import io.itgen.model.tasks.Tasks;
import io.itgen.services.TaskService;
import io.itgen.tests.TestBase;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TaskManualDeleteInStack extends TestBase {

  TaskService taskService = new TaskService();

  @BeforeMethod
  public void ensurePreconditions() {
    app.trTask()
        .newManualTask(
            "DeleteInStack",
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

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTaskManualDeleteInStack() {
    app.goTo().menuTasks();
    Tasks before = app.dbtasks().tasks();
    app.task().deleteInStack();
    Tasks after = app.dbtasks().tasks();
    assertThat(after.size(), equalTo(before.size() - 1));
    app.goTo().menuSchedule();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    taskService.drop();
  }
}
