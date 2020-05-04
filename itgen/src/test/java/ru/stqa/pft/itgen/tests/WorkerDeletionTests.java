package ru.stqa.pft.itgen.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.model.WorkerData;
import ru.stqa.pft.itgen.model.Workers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class WorkerDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().workers().size() == 0) {
      app.goTo().tasks();
      app.goTo().gotoWorker();
      app.workers().createFirstWorker(new WorkerData().withFirstName("Маша").withLastName("Машина").withRole("empolyee")
              .withPhone("8962988888888"));
    }
  }

  @Test
  public void testWorkerDeletion() {
    app.goTo().tasks();
    app.goTo().gotoWorker();
    Workers before = app.db().workers();
    WorkerData deletedWorker = before.iterator().next();
    app.workers().deletionWorker(deletedWorker);
    Workers after = app.db().workers();
    assertThat(after, equalTo(before.without(deletedWorker)));
    verifyWorkerListInUI();
  }
}