package io.itgen.tests;
//Тест на модификацию работника. Для подключения проверки на соответствие ui и бд в конфигурации
// запуска указываем -DverifyUI=true.

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.itgen.general.TimeGeneral;
import io.itgen.model.users.Contacts;
import io.itgen.services.WorkerService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.itgen.model.WorkerData;
import io.itgen.model.Workers;
import io.itgen.model.users.Emails;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class WorkerModificationTests extends TestBase {
  public WorkerData modifydWorker;

  @DataProvider
  public Iterator<Object[]> validWorkersFromJson() throws IOException {
    try (BufferedReader reader =
                 new BufferedReader(new FileReader(new File("src/test/resources/testdata/workers_modification.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<WorkerData> workers = gson.fromJson(json, new TypeToken<List<WorkerData>>() {
      }.getType()); // List<WorkerData>.class
      return workers.stream().map((w) -> new Object[]{w}).collect(Collectors.toList()).iterator();
    }
  }

  @BeforeMethod
  public void ensurePreconditions() {
    WorkerService workerService = new WorkerService();
    TimeGeneral time = new TimeGeneral();
    modifydWorker = new WorkerData().withId("workerModify").withFirstName("Маша").withLastName("Машина")
            .withRoles(Arrays.asList("employee"))
            .withCountry("AL").withTimeZone("Europe/Minsk")
            .withLocate("ru")
            .withGender(2)
            .withStartDay(new Date())
            .withBirthday(new Date(1556726891000L))
            .withLangs(Arrays.asList("ru"))
            .withContacts(Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withEmails(Collections.singletonList(new Emails().withAddress("julja83@list.ru").withVerified(true)));
    workerService.create(modifydWorker);
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
    WorkerService workerService = new WorkerService();
    workerService.findByIdAndDelete("workerModify");
  }

}
