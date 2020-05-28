package ru.stqa.pft.itgen.tests;
//Тест на удаление работника. Для подключения проверки на соответствие ui и бд в конфигурации
// запуска указываем -DverifyUI=true.
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.WorkerData;
import ru.stqa.pft.itgen.model.Workers;
import ru.stqa.pft.itgen.model.users.Contacts;
import ru.stqa.pft.itgen.model.users.Emails;
import ru.stqa.pft.itgen.services.WorkerService;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class WorkerDeletionTests extends TestBase {
  public WorkerData deletedWorker;

  @BeforeMethod
  public void ensurePreconditions() {
    WorkerService workerService = new WorkerService();
    deletedWorker = new WorkerData().withId("workerDelete").withFirstName("Маша").withLastName("Машина")
            .withRoles(Arrays.asList("employee"))
            .withCountry("AL").withTimeZone("Europe/Minsk")
            .withLocate("ru")
            .withBirthday(new Date(1556726891000L))
            .withLangs(Arrays.asList("ru"))
            .withContacts(Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withEmails(Collections.singletonList(new Emails().withAddress("julja83@list.ru").withVerified(true)));
    workerService.create(deletedWorker);
  }

  @Test
  public void testWorkerDeletion() {
    app.goTo().menuTasks();
    app.goTo().menuWorkers();
    Workers before = app.db().workers();
    app.worker().deletionWorker("workerDelete");
    Workers after = app.db().workers();
    assertThat(after.size(),equalTo(before.size()-1));
    assertThat(after, equalTo(before.without(deletedWorker)));
    verifyWorkerListInUI();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    WorkerService workerService = new WorkerService();
    workerService.findByIdAndDelete("workerDelete");
  }
}