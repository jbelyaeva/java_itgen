package tests.candidates;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import core.general.RunTestAgain;
import data.model.candidate.CandidateData;
import data.model.candidate.Candidates;
import data.services.CandidateService;
import data.services.TaskService;
import app.testbase.TestBase;
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

    app.trCandidate()
        .saveCandidate(
            "ChangeStatus",
            "Света",
            "Светина",
            "Sveta",
            "Svetina",
            app.base().DateWithCorrectionDays(-7300),
            1,
            oldStatus,
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
    app.trCandidate()
        .saveCandidate(
            "ChangeStatus",
            "Света",
            "Светина",
            "Sveta",
            "Svetina",
            app.base().DateWithCorrectionDays(-7300),
            1,
            newStatus,
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
    CandidateData candidateAdd = candidateService.findById("ChangeStatus");

    for (CandidateData candidateAfter : after) {
      if (candidateAfter.getId().equals("ChangeStatus")) {
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
