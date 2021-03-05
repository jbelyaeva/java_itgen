package tests.candidates;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.tasks.TaskData;
import data.model.tasks.Tasks;
import data.services.CandidateService;
import data.services.TaskService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CandidateDoneTaskInTabTask extends TestBase {

  CandidateService candidateService = new CandidateService();
  TaskData newTask = null;
  private final TaskService taskService = new TaskService();

  @BeforeMethod
  public void ensurePreconditions() {
    data.candidates().set1_newCandidate("trainer", "test1");
    data.tasksManual().set1_newManualTaskCandidate("Связаться с кандидатом", "open");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCandidateDoneTaskInTabTask() {
    app.goTo().urlCandidates();
    Tasks beforeTask = app.dbtasks().tasks();
    app.cantidate().doneTask("candidate");
    app.goTo().menuTasks();
    Tasks afterTask = app.dbtasks().tasks();
    assertThat(afterTask.size(), equalTo(beforeTask.size()));
    newTask = app.dbtasks().lastTask();
    check(afterTask);
  }

  private void check(Tasks afterTask) {
    data.tasksManual().set2_doneNewManualTaskCandidate("Связаться с кандидатом", "closed");
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
