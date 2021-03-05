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

public class CandidateStatusNewToDifferentStatuses extends TestBase {

  CandidateService candidateService = new CandidateService();
  TaskService taskService = new TaskService();
  String oldStatus = "new";
  String newStatus = "";

  @BeforeMethod
  public void ensurePreconditions() {
    data.candidates().set1_newCandidate("trainer", oldStatus);
    app.base().refresh();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCandidateStatusNewToWrote() {
    newStatus = "wait";
    app.goTo().menuCandidates();
    Candidates before = app.dbcandidates().candidates();
    app.cantidate().changeStatusOnWait();
    Candidates after = app.dbcandidates().candidates();
    assertThat(after.size(), equalTo(before.size()));
    check(after, newStatus);
    app.goTo().menuTasks();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCandidateStatusNewToTest1() {
    app.goTo().menuCandidates();
    newStatus = "test1";
    app.goTo().urlCandidates();
    Candidates before = app.dbcandidates().candidates();
    app.cantidate().changeStatusOnTest1();
    Candidates after = app.dbcandidates().candidates();
    assertThat(after.size(), equalTo(before.size()));
    check(after, newStatus);
    app.goTo().menuTasks();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCandidateStatusNewToSuspend() {
    newStatus = "suspend";
    app.goTo().menuCandidates();
    Candidates before = app.dbcandidates().candidates();
    app.cantidate().changeStatusOnSuspend();
    Candidates after = app.dbcandidates().candidates();
    assertThat(after.size(), equalTo(before.size()));
    check(after, newStatus);
    app.goTo().menuTasks();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCandidateStatusNewToLeave() {
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
