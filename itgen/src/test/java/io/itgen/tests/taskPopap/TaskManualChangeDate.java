package io.itgen.tests.taskPopap;
// Таска на сейчас переносится на завтра

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

public class TaskManualChangeDate extends TestBase {

  private final TaskService taskService = new TaskService();
  private TaskData taskClean = null;
  private final Date createAt = new Date();
  private final long duoDateSort = new Date().getTime() + 86400000; //на завтра
  private final Date duoDateWithTime = new Date(duoDateSort);
  private Date[] dates = null;
  private final String[] texts = null;
  private final String[] clients = null;
  private final String[] commentaries = null;

  @BeforeMethod
  public void ensurePreconditions() {
    app.trTask()
        .newManualTask(
            "PopupChangeDateTask",
            "777",
            "666",
            "Записать на пробное",
            1,
            createAt,
            "open",
            new Date(),
            new Date().getTime(),
            "21");
  }

  @Test (retryAnalyzer = RunTestAgain.class)
  public void testTaskManualChangeDate() {
    app.goTo().menuTasks();
    Tasks before = app.dbtasks().tasks();
    app.task().changeDateManualTaskInPopup();
    Tasks after = app.dbtasks().tasks();
    taskClean = app.dbtasks().lastTask();
    assertThat(after.size(), equalTo(before.size()));
    check(after);
    app.task().refresh();
    app.goTo().menuSchedule();
  }

  private void check(Tasks after) {
    dates = new Date[]{createAt, duoDateWithTime};
    app.trTask()
        .saveManualTask(
            "PopupChangeDateTask",
            "Записать на пробное",
            createAt,
            "open",
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
            "newTask_changeDateTask");

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
