package tests.candidates;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import core.general.RunTestAgain;
import data.model.candidate.CandidateData;
import data.model.candidate.Candidates;
import data.model.users.TrainerData;
import data.model.users.Trainers;
import data.services.CandidateService;
import data.services.TaskService;
import data.services.TrainerService;
import app.testbase.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CandidateCreateEmployeer extends TestBase {

  CandidateService candidateService = new CandidateService();
  TaskService taskService = new TaskService();
  TrainerService trainerService = new TrainerService();
  TrainerData cleanTrainer = null;
  String oldStatus = "interview";
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
            "mail@mail11.com",
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
  public void testCandidateCreateEmployeer() {
    newStatus = "success";
    app.goTo().menuCandidates();
    Trainers beforeTrainers = app.db().trainers();
    Candidates before = app.dbcandidates().candidates();
    app.cantidate().createEmployee(before.stream().findFirst().get().getFirstName());
    Trainers afterTrainers = app.db().trainers();
    Candidates after = app.dbcandidates().candidates();
    cleanTrainer = app.db().lastTrainer();
    assertThat(after.size(), equalTo(before.size()));
    assertThat(afterTrainers.size(), equalTo(beforeTrainers.size() + 1));
    check(after, newStatus);
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
            "mail@mail11.com",
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
    trainerService.DeleteById(cleanTrainer.getId());
  }
}
