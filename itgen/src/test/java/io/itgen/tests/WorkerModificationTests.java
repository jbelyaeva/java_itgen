package io.itgen.tests;
// Тест на модификацию работника. Для подключения проверки на соответствие ui и бд в конфигурации
// запуска указываем -DverifyUI=true.

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.itgen.model.WorkerData;
import io.itgen.model.Workers;
import io.itgen.services.WorkerService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WorkerModificationTests extends TestBase {

  public WorkerData modifydWorker;
  WorkerService workerService = new WorkerService();

  @DataProvider
  public Iterator<Object[]> validWorkersFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/workers_modification.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<WorkerData> workers =
          gson.fromJson(
              json, new TypeToken<List<WorkerData>>() {
              }.getType()); // List<WorkerData>.class
      return workers.stream().map((w) -> new Object[]{w}).collect(Collectors.toList()).iterator();
    }
  }

  @BeforeMethod
  public void ensurePreconditions() {
    app.trWorker().saveNewWorker("workerModify", "Маша", "Машина", "employee",
        "AL", "Europe/Minsk", "ru", "ru", "1234567899", "julja83@list.ru");
    modifydWorker = workerService.findById("workerModify");
  }

  @Test(dataProvider = "validWorkersFromJson")
  public void testWorkerModification(WorkerData worker) {
    app.goTo().menuTasks();
    app.goTo().menuWorkers();
    Workers before = app.db().workers();
    app.worker().modificationWorker(worker, "workerModify");
    Workers after = app.db().workers();
    assertThat(after.size(), equalTo(before.size()));
    WorkerData workerAdd = worker.withId(modifydWorker.getId());
    assertThat(after, equalTo(before.without(modifydWorker).withAdded(workerAdd)));
    app.goTo().menuWorkers();
    verifyWorkerListInUI();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    workerService.DeleteById("workerModify");
  }
}
