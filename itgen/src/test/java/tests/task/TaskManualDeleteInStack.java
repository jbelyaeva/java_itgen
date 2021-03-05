package tests.task;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.tasks.Tasks;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TaskManualDeleteInStack extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.tasksManual()
        .set6_1_ManualTaskCreatorAdminAssigneeSuperPr0("task", "Записать на пробное", "open", "21");
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
    data.clean().taskAndSchedule();
  }
}
