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

public class TaskAutoLeaveCommentInPopup extends TestBase {

  private final TaskService taskService = new TaskService();
  private TaskData taskClean = null;
  private final Date createAt = new Date();
  private final Date duoDateWithTime = new Date();
  private final long duoDateSort = new Date().getTime();
  private Date[] dates = null;
  private final String[] texts = null;
  private final String[] clients = null;
  private String[] commentaries = null;

  @BeforeMethod
  public void ensurePreconditions() {
    app.trTask()
        .saveAutoTask(
            "AutoTaskLeaveCommentInPopup",
            "contactForPayment",
            createAt,
            "inProgress",
            duoDateWithTime,
            duoDateSort,
            "666",
            "21",
            "21",
            "21.00 : 23.00",
            dates,
            texts,
            clients,
            commentaries,
            "newAutoTask_takeAutoTask");
  }

  @Test
  public void testTaskAutoLeaveCommentInPopup() {
    app.goTo().menuTasks();
    Tasks before = app.dbtasks().tasks();
    app.task().leaveCommentTask("Комментарий, comments");
    Tasks after = app.dbtasks().tasks();
    taskClean = app.dbtasks().lastTask();
    assertThat(after.size(), equalTo(before.size()));
    check(after);
    app.goTo().menuSchedule();
  }

  private void check(Tasks after) {
    commentaries = new String[]{"Комментарий, comments", "666"};
    app.trTask()
        .saveAutoTask(
            "AutoTaskLeaveCommentInPopup",
            "contactForPayment",
            createAt,
            "inProgress",
            duoDateWithTime,
            duoDateSort,
            "666",
            "21",
            "666",
            "21.00 : 23.00",
            dates,
            texts,
            clients,
            commentaries,
            "newTask_leaveAutoCommentTask");

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
