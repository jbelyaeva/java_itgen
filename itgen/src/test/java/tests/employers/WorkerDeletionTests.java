package tests.employers;
// Тест на удаление работника. Для подключения проверки на соответствие ui и бд в конфигурации
// запуска указываем -DverifyUI=true.

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import data.model.users.WorkerData;
import data.model.users.Workers;
import data.services.WorkerService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WorkerDeletionTests extends TestBase {

  public WorkerData deletedWorker;
  WorkerService workerService = new WorkerService();

  @BeforeMethod
  public void ensurePreconditions() {
    data.newWorker().set1_NewWorker();
    deletedWorker = workerService.findById("newWorker");
  }

  @Test
  public void testWorkerDeletion() {
    app.goTo().menuTasks();
    app.goTo().menuWorkers();
    Workers before = app.db().workers();
    app.worker().deletionWorker("newWorker");
    Workers after = app.db().workers();
    assertThat(after.size(), equalTo(before.size() - 1));
    assertThat(after, equalTo(before.without(deletedWorker)));
    verifyWorkerListInUI();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().worker();
  }
}
