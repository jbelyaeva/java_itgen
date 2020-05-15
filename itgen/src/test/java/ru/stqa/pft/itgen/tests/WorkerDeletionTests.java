package ru.stqa.pft.itgen.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.WorkerData;
import ru.stqa.pft.itgen.model.Workers;
import ru.stqa.pft.itgen.services.WorkerService;

import java.util.Collections;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class WorkerDeletionTests extends TestBase {
  public WorkerData deletedWorker;

  @BeforeMethod
  public void ensurePreconditions() {
    WorkerService workerService = new WorkerService();
    WorkerData worker = new WorkerData().withId("workerDelete").withFirstName("Маша").withLastName("Машина")
            .withRoles(Collections.singletonList(new WorkerData.Roles().withRoles("employee")))
            .withCountry("AL").withTimeZone("Europe/Minsk")
            .withLocate("ru")
            .withBirthday(new Date(1556726891000L))
            .withLangs(Collections.singletonList(new WorkerData.Langs().withLangs("ru")))
            .withContacts(Collections.singletonList(new WorkerData.Contacts().withType("phone").withVal("1234567899")))
            .withEmails(Collections.singletonList(new WorkerData.Emails().withAddress("julja83@list.ru").withVerified(true)));
    workerService.create(worker);
  }

  @Test
  public void testWorkerDeletion() {
    app.goTo().menuTasks();
    app.goTo().menuWorkers();
    Workers before = app.db().workers();
    deletedWorker = app.worker().findWorker("workerDelete");
    app.worker().deletionWorker(deletedWorker);
    Workers after = app.db().workers();
    assertThat(after.size(),equalTo(before.size()-1));
    assertThat(after, equalTo(before.without(deletedWorker)));
    verifyWorkerListInUI();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    WorkerService workerService = new WorkerService();
    WorkerData workerClean = workerService.findById("workerDelete");
    if (workerClean != null) {
      workerService.delete(workerClean);
    }
  }
}