package io.itgen.tests.candidates;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.general.RunTestAgain;
import io.itgen.model.CandidateData;
import io.itgen.model.Candidates;
import io.itgen.services.CandidateService;
import io.itgen.services.TaskService;
import io.itgen.tests.TestBase;
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
