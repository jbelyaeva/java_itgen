package io.itgen.tests.taskPopap;

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

public class TaskManualDeleteInPopup extends TestBase {

  TaskService taskService = new TaskService();
  Date createAt = new Date();
  Date duoDateWithTime = new Date();
  long duoDateSort = new Date().getTime();

  @BeforeMethod
  public void ensurePreconditions() {
    app.trTask()
        .newManualTask(
            "PopupDeleteTask",
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

  @Test
  public void testTaskManualDeleteInPopup() {
    app.goTo().menuTasks();
    Tasks before = app.dbtasks().tasks();
    app.task().deleteTaskInPopup();
    Tasks after = app.dbtasks().tasks();
    assertThat(after.size(), equalTo(before.size() - 1));
    app.goTo().menuSchedule();
  }


  @AfterMethod(alwaysRun = true)
  public void clean() {
    if (taskService.findById("PopupDeleteTask") != null) {
      taskService.DeleteById("PopupDeleteTask");
    }
  }
}
