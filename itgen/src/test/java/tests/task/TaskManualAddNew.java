package tests.task;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.tasks.TaskData;
import data.model.tasks.Tasks;
import data.provides.LocaleUtilsTestData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TaskManualAddNew extends TestBase {

  public void ensurePreconditions() {
    data.clean().taskAndSchedule();
  }

  @Test(dataProvider = "validTaskFromJson", dataProviderClass = LocaleUtilsTestData.class, retryAnalyzer = RunTestAgain.class)
  public void testAddNewTask(TaskData task) {
    app.goTo().menuTrainers();
    app.goTo().menuTasks();
    Tasks before = app.dbtasks().tasks();
    app.task().addNewTask(task);
    Tasks after = app.dbtasks().tasks();
    assertThat(after.size(), equalTo(before.size() + 1));
    //добавить проверки через ui!
    app.goTo().menuSchedule();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule();
  }
}
