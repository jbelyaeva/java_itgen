package io.itgen.tests;
// Тест на удаление работника. Для подключения проверки на соответствие ui и бд в конфигурации
// запуска указываем -DverifyUI=true.

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.model.WorkerData;
import io.itgen.model.Workers;
import io.itgen.services.WorkerService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WorkerDeletionTests extends TestBase {

  public WorkerData deletedWorker;

  @BeforeMethod
  public void ensurePreconditions() {
    app.trWorker().saveNewWorker("worker", "Маша", "Машина", "employee",
        "AL", "Europe/Minsk", "ru", "ru", "1234567899", "julja83@list.ru");
  }

  @Test
  public void testWorkerDeletion() {
    app.goTo().menuTasks();
    app.goTo().menuWorkers();
    Workers before = app.db().workers();
    app.worker().deletionWorker("workerDelete");
    Workers after = app.db().workers();
    assertThat(after.size(), equalTo(before.size() - 1));
    assertThat(after, equalTo(before.without(deletedWorker)));
    verifyWorkerListInUI();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    WorkerService workerService = new WorkerService();
    workerService.DeleteById("workerDelete");
  }
}
