package tests.employers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.users.TrainerData;
import data.model.users.Trainers;
import data.services.TrainerService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerDeletionTests extends TestBase {
  public TrainerData deletedTrainer;

  @BeforeMethod
  public void ensurePreconditions() {
    data.newWorker().set3_Trainer();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTrainerDeletion() {
    app.goTo().menuTrainers();
    Trainers before = app.db().trainers();
    deletedTrainer = data.trainerService().findById("newTrainer");
    app.trainer().delete("newTrainer");
    Trainers after = app.db().trainers();
    assertThat(after, equalTo(before.without(deletedTrainer)));
    verifyTrainerListInUI();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    TrainerService trainerService = new TrainerService();
    trainerService.DeleteById("newTrainer");
  }
}
