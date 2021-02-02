package tests.candidates;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.tasks.TaskData;
import data.model.tasks.Tasks;
import data.services.CandidateService;
import data.services.TaskService;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CandidateDoneTaskInTabTask extends TestBase {

  CandidateService candidateService = new CandidateService();
  TaskData newTask = null;
  private final TaskService taskService = new TaskService();
  private final Date createAt = new Date();
  private final Date duoDateWithTime = new Date();
  private final long duoDateSort = new Date().getTime();
  private final Date[] dates = null;
  private final String[] texts = null;
  private final String[] clients = null;
  private final String[] commentaries = null;

  @BeforeMethod
  public void ensurePreconditions() {

    app.trCandidate()
        .saveCandidate(
            "DoneTask",
            "Света",
            "Светина",
            "Sveta",
            "Svetina",
            app.base().DateWithCorrectionDays(-7300),
            1,
            "test1",
            "trainer",
            "AM",
            "City",
            "Europe/Monaco",
            "ru",
            "Хороший кандидат, плохой кандидат",
            "Очень много говорит",
            "14",
            "http://www.yandex.ru",
            "http://www.yandex.ru",
            "1111111111",
            "mail@mail.com",
            "+56756756756",
            "+79896667845",
            "111111111111111",
            "+5674545453",
            "+9998764534",
            "+00078566664",
            "+890000066432",
            "0000000000000",
            "010101010101",
            "cat",
            "key",
            null,
            "good",
            "mother");

    app.trTask()
        .newManualTask(
            "DoneTaskCandidate",
            "666",
            "666",
            "Связаться с кандидатом",
            1,
            new Date(),
            "open",
            new Date(),
            new Date().getTime(),
            null);
    taskService.updateField("DoneTaskCandidate", "linkCandidate", "DoneTask");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCandidateDoneTaskInTabTask() {
    app.goTo().urlCandidates();

    Tasks beforeTask = app.dbtasks().tasks();

    app.cantidate().doneTask("DoneTask");
    app.goTo().menuTasks();

    Tasks afterTask = app.dbtasks().tasks();

    assertThat(afterTask.size(), equalTo(beforeTask.size()));

    newTask = app.dbtasks().lastTask();
    check(afterTask);
  }

  private void check(Tasks afterTask) {

    app.trTask()
        .saveManualTask(
            "DoneTaskCandidate",
            "Связаться с кандидатом",
            createAt,
            "closed",
            duoDateWithTime,
            duoDateSort,
            "666",
            null,
            "666",
            "666",
            1,
            dates,
            texts,
            clients,
            commentaries,
            "newTask_doneTask");
    taskService.updateField(newTask.getId(), "linkCandidate", "DoneTask");
    TaskData taskAdd = taskService.findById(newTask.getId());

    for (TaskData task : afterTask) {
      if (task.getId().equals(newTask.getId())) {
        assertThat(afterTask, equalTo(afterTask.without(task).withAdded(taskAdd)));
        return;
      }
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    candidateService.drop();
    taskService.drop();
  }
}
