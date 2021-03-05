package tests.candidates;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.candidate.CandidateData;
import data.model.candidate.Candidates;
import data.services.CandidateService;
import data.services.TaskService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CandidateStatusTest1ToDifferentStatuses extends TestBase {

  CandidateService candidateService = new CandidateService();
  TaskService taskService = new TaskService();
  String oldStatus = "test1";
  String newStatus = "";

  @BeforeMethod
  public void ensurePreconditions() {
    data.candidates().set1_newCandidate("trainer", oldStatus);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCandidateStatusTest1ToNew() {
    newStatus = "new";
    app.goTo().menuCandidates();
    Candidates before = app.dbcandidates().candidates();
    app.cantidate().changeStatusOnNew();
    Candidates after = app.dbcandidates().candidates();
    assertThat(after.size(), equalTo(before.size()));
    check(after, newStatus);
    app.goTo().menuTasks();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCandidateStatusTest1ToInterview() {
    newStatus = "interview";
    app.goTo().menuCandidates();
    Candidates before = app.dbcandidates().candidates();
    app.cantidate().changeStatusOnInterview();
    Candidates after = app.dbcandidates().candidates();
    assertThat(after.size(), equalTo(before.size()));
    check(after, newStatus);
    app.goTo().menuTasks();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCandidateStatusTest1ToLeave() {
    newStatus = "leave";
    app.goTo().menuCandidates();
    Candidates before = app.dbcandidates().candidates();
    app.cantidate().changeStatusOnLeave();
    Candidates after = app.dbcandidates().candidates();
    assertThat(after.size(), equalTo(before.size()));
    check(after, newStatus);
    app.goTo().menuTasks();
  }

  private void check(Candidates after, String newStatus) {
    data.candidates().set1_newCandidate("trainer", newStatus);
    CandidateData candidateAdd = candidateService.findById("candidate");

    for (CandidateData candidateAfter : after) {
      if (candidateAfter.getId().equals("candidate")) {
        assertThat(after, equalTo(after.without(candidateAfter).withAdded(candidateAdd)));
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
