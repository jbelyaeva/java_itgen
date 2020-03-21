package ru.stqa.pft.itgen.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class WorkerDeletionTests extends TestBase {

  @Test
  public void testWorkerDeletion() {
    app.getNavigationHelper().gotoWorker();
    int before = app.getWorkerHelper().getWorkerCount();
    app.getWorkerHelper().selectedWorker();
    app.getWorkerHelper().deleteWorker();
    app.getWorkerHelper().assertDeleteSelectedWorker();
    app.getNavigationHelper().gotoWorker();
    int after = app.getWorkerHelper().getWorkerCount();
    Assert.assertEquals(after, before - 1);
  }
}