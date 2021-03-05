package tests.employers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.users.TrainerData;
import data.model.users.Trainers;
import data.model.users.WorkerData;
import data.model.users.Workers;
import data.provides.LocaleUtilsTestData;
import data.services.TrainerService;
import data.services.WorkerService;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class WorkerAndTrainerCreationTests extends TestBase {

  String idWorker = "null";
  String idTrainer = "null";

  @Test(dataProvider = "validWorkersCreateFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testWorkerCreation(WorkerData worker) {
    app.goTo().menuTrainers();
    app.goTo().menuWorkers();
    Workers before = app.db().workers();
    String url = app.worker().createWorker(worker);
    idWorker = app.worker().getId(url);
    Workers after = app.db().workers();
    assertThat(after.size(), equalTo(before.size() + 1));
    WorkerData workerAdd = worker.withId(idWorker);
    assertThat(after, equalTo(before.withAdded(workerAdd)));
    verifyWorkerListInUI();
  }

  @Test(dataProvider = "validWorkersAdminsFromJson", dataProviderClass = LocaleUtilsTestData.class, enabled = false)
  public void testWorkerAdminCreation(WorkerData worker) {
    app.goTo().menuTasks();
    app.goTo().menuWorkers();
    int before = app.worker().getWorkerCount();
    app.worker().createWorker(worker);
    int after = app.worker().getWorkerCount();
    Assert.assertEquals(after, before + 1);
  }

  @Test(dataProvider = "validTrainersCreateFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testWorkerTrainerCreation(TrainerData trainer) {
    app.goTo().menuTasks();
    app.goTo().menuWorkers();
    Trainers before = app.db().trainers();
    String url = app.trainer().create(trainer);
    idTrainer = app.trainer().getId(url);
    Trainers after = app.db().trainers();
    assertThat(after.size(), equalTo(before.size() + 1));
    TrainerData trainerAdd = trainer.withId(idTrainer);
    assertThat(after, equalTo(before.withAdded(trainerAdd)));
    verifyTrainerListInUI();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    WorkerService workerService = new WorkerService();
    workerService.DeleteById(idWorker);
    TrainerService trainerService = new TrainerService();
    trainerService.DeleteById(idTrainer);
  }
}
