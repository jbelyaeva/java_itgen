package tests.candidates;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import data.model.candidate.CandidateData;
import data.model.candidate.Candidates;
import data.model.users.TrainerData;
import data.model.users.Trainers;
import data.services.CandidateService;
import data.services.TaskService;
import data.services.TrainerService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CandidateCreateEmployeer extends TestBase {

  CandidateService candidateService = new CandidateService();
  TaskService taskService = new TaskService();
  TrainerService trainerService = new TrainerService();
  TrainerData cleanTrainer = null;
  String newStatus = "";

  @BeforeMethod
  public void ensurePreconditions() {
    data.candidates().set1_newCandidate("trainer", "interview");
  }

  @Test
  public void testCandidateCreateEmployer() {
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
    data.candidates().set1_newCandidate("trainer", "success");
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
    trainerService.DeleteById(cleanTrainer.getId());
  }
}
