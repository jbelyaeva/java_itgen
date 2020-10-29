package io.itgen.tests.taskPopup;
// меняем в таске студента на студента

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.general.RunTestAgain;
import io.itgen.model.tasks.TaskData;
import io.itgen.model.tasks.Tasks;
import io.itgen.services.FamilyService;
import io.itgen.services.StudentService;
import io.itgen.services.TaskService;
import io.itgen.tests.TestBase;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TaskAutoChangeClient extends TestBase {

  private final StudentService studentService = new StudentService();
  private final FamilyService familyService = new FamilyService();
  private final TaskService taskService = new TaskService();
  private TaskData taskClean = null;
  private final Date createAt = new Date();
  private final Date duoDateWithTime = new Date();
  private final long duoDateSort = new Date().getTime();
  private final Date[] dates = null;
  private final String[] texts = null;
  private String[] clients = null;
  private final String[] commentaries = null;

  @BeforeMethod
  public void ensurePreconditions() {
    app.trFamily().newFamily("Student", false, "txc");

    app.trStudent()
        .newStudent(
            "Student", "Маша", "Машина", "expert", "AL", "Europe/Minsk", 2, "ru", "ru", "Student");

    app.trTask()
        .saveAutoTask(
            "AutoTaskChangeClient",
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

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTaskAutoChangeClient() throws InterruptedException {
    app.goTo().menuTasks();
    Tasks before = app.dbtasks().tasks();
    app.task().changeClientAutoTaskInPopup("Машина Маша");
    Tasks after = app.dbtasks().tasks();
    taskClean = app.dbtasks().lastTask();
    assertThat(after.size(), equalTo(before.size()));
    check(after);
    app.goTo().menuSchedule();
  }

  private void check(Tasks after) {
    clients = new String[] {"21"};
    app.trTask()
        .saveAutoTask(
            "AutoTaskChangeClient",
            "contactForPayment",
            createAt,
            "inProgress",
            duoDateWithTime,
            duoDateSort,
            "666",
            "Student",
            "666",
            "21.00 : 23.00",
            dates,
            texts,
            clients,
            commentaries,
            "takeAutoTask_changeClientAutoTask");

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
    taskService.drop();
    studentService.DeleteById("Student");
    familyService.DeleteById("Student");
  }
}
