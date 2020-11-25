package tests.candidates;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.candidate.CandidateData;
import data.model.candidate.Candidates;
import data.model.tasks.TaskData;
import data.model.tasks.Tasks;
import data.services.CandidateService;
import data.services.TaskService;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CandidateCreateTask extends TestBase {

  CandidateService candidateService = new CandidateService();
  TaskService taskService = new TaskService();
  TaskData newTask = null;
  String title = "Связаться с кандидатом";

  @BeforeMethod
  public void ensurePreconditions() {

    app.trCandidate()
        .saveCandidate(
            "CreateTask",
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
  }

  @Test(retryAnalyzer = RunTestAgain.class, enabled = false)
  public void testCandidateCreateTask() {
    app.goTo().urlCandidates();

    Candidates before = app.dbcandidates().candidates();
    Tasks beforeTask = app.dbtasks().tasks();

    app.cantidate().createTask(title);

    Candidates after = app.dbcandidates().candidates();
    Tasks afterTask = app.dbtasks().tasks();

    assertThat(after.size(), equalTo(before.size()));
    assertThat(afterTask.size(), equalTo(beforeTask.size() + 1));

    newTask = app.dbtasks().lastTask();
    check(after, afterTask);
  }

  private void check(Candidates after, Tasks afterTask) {
    app.trCandidate()
        .saveCandidate(
            "CreateTask",
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
    CandidateData candidateAdd = candidateService.findById("CreateTask");

    for (CandidateData candidateAfter : after) {
      if (candidateAfter.getId().equals("CreateTask")) {
        assertThat(after, equalTo(after.without(candidateAfter).withAdded(candidateAdd)));
        return;
      }
    }

    app.trTask()
        .newManualTask(
            newTask.getId(),
            "666",
            "666",
            title,
            1,
            new Date(),
            "open",
            new Date(),
            new Date().getTime(),
            null);
    taskService.updateField(newTask.getId(), "linkCandidate", "CreateTask");
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
