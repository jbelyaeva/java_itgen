package tests.employers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.users.TrainerData;
import data.model.users.Trainers;
import data.provides.LocaleUtilsTestData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerModificationTests extends TestBase {

  public TrainerData modifyTrainer;

  @BeforeMethod
  public void ensurePreconditions() {
    data.newWorker().set3_Trainer();
  }

  @Test(dataProvider = "validWorkersTrainersFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testTrainerModification(TrainerData trainer) {
    app.goTo().menuTrainers();
    Trainers before = app.db().trainers();
    app.trainer().goUrlTrainer("newTrainer");
    modifyTrainer = data.trainerService().findById("newTrainer");
    app.trainer().modify(trainer);
    Trainers after = app.db().trainers();
    assertThat(after.size(), equalTo(before.size()));
    TrainerData trainerAdd = trainer.withId("newTrainer");
    assertThat(after, equalTo(before.without(modifyTrainer).withAdded(trainerAdd)));
    verifyTrainerListInUI();
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().trainer();
  }
}
