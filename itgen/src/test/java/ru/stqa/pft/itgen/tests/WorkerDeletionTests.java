package ru.stqa.pft.itgen.tests;

import org.testng.annotations.Test;

public class WorkerDeletionTests extends TestBase {

  @Test
  public void testWorkerDeletion() {
    app.getNavigationHelper().gotoWorker();
    app.getWorkerHelper().selectedWorker();
    app.getWorkerHelper().deleteWorker();
    app.getWorkerHelper().assertDeleteSelectedWorker();
  }
}