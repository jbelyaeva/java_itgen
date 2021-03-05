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
  data.candidates().set1_newCandidate("trainer", "test1");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCandidateCreateTask() {
    app.goTo().menuCandidates();
    Candidates before = app.dbcandidates().candidates();
    Tasks beforeTask = app.dbtasks().tasks();
    app.cantidate().createTask(title);
    app.goTo().menuTasks();
    Candidates after = app.dbcandidates().candidates();
    Tasks afterTask = app.dbtasks().tasks();
    assertThat(after.size(), equalTo(before.size()));
    newTask = app.dbtasks().lastTask();
    assertThat(afterTask.size(), equalTo(beforeTask.size() + 1));
    check(after, afterTask);
  }

  private void check(Candidates after, Tasks afterTask) {
    data.candidates().set1_newCandidate("trainer", "test1");
    CandidateData candidateAdd = candidateService.findById("candidate");

    for (CandidateData candidateAfter : after) {
      if (candidateAfter.getId().equals("candidate")) {
        assertThat(after, equalTo(after.without(candidateAfter).withAdded(candidateAdd)));
        return;
      }
    }
    data.tasksManual().set1_newManualTaskCandidate("Связаться с кандидатом", "open");
    taskService.updateField(newTask.getId(), "linkCandidate", "candidate");
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
