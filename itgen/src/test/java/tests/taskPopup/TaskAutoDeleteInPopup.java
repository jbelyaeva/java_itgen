package tests.taskPopup;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.tasks.Tasks;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TaskAutoDeleteInPopup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.tasksAuto().set1_newAutoTaskToday("task", "contactForPayment", "inProgress", "21");
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
    data.clean().taskAndSchedule();
  }
}
