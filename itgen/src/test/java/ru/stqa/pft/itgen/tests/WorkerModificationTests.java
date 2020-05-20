package ru.stqa.pft.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.WorkerData;
import ru.stqa.pft.itgen.model.Workers;
import ru.stqa.pft.itgen.services.WorkerService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
    WorkerData worker = new WorkerData().withId("workerModify").withFirstName("Маша").withLastName("Машина")
            .withRoles(Collections.singletonList(new WorkerData.Roles().withRoles("employee")))
            .withCountry("AL").withTimeZone("Europe/Minsk")
            .withLocate("ru")
            .withBirthday(new Date(1556726891000L))
            .withStartWork(new Date(1556726891000L))
            .withContacts(Collections.singletonList(new WorkerData.Contacts().withType("phone").withVal("1234567899")))
            .withEmails(Collections.singletonList(new WorkerData.Emails().withAddress("julja83@list.ru").withVerified(true)));
    workerService.create(worker);
  }

  @Test(dataProvider = "validWorkersFromJson")
  public void testWorkerModification(WorkerData worker) {
    app.goTo().menuTasks();
    app.goTo().menuWorkers();
    Workers before = app.db().workers();
    modifydWorker = app.worker().findWorker("workerModify");
    app.worker().modificationWorker(worker, modifydWorker);
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
    WorkerData workerClean = workerService.findById("workerModify");
    if (workerClean != null) {
      workerService.delete(workerClean);
    }
  }
}
