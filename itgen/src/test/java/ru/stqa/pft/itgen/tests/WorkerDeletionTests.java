package ru.stqa.pft.itgen.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.WorkerData;
import ru.stqa.pft.itgen.model.Workers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class WorkerDeletionTests extends TestBase {
  public WorkerData deletedWorker;

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().workers().size() == 0) {
      app.goTo().menuTasks();
      app.goTo().menuWorkers();
      app.worker().createFirstWorker(new WorkerData().withFirstName("Маша").withLastName("Машина").withRole("empolyee")
              .withPhone("8962988888888"));
    }
  }

  @Test
  public void testWorkerDeletion() {
    app.goTo().menuTasks();
    app.goTo().menuWorkers();
    Workers before = app.db().workers();
    for (WorkerData worker1 : before) {
      String id = worker1.getId();
      if ((!id.equals("777")) && (!id.equals("666"))) {
        deletedWorker = worker1;
        break;
      }
    }
    app.worker().deletionWorker(deletedWorker);
    Workers after = app.db().workers();
    assertThat(after, equalTo(before.without(deletedWorker)));
    verifyWorkerListInUI();
  }
}