package ru.stqa.pft.itgen.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.WorkerData;

public class WorkerAndTrainerCreationTests extends TestBase {

  @Test
  public void testWorkerCreation() {
    app.getNavigationHelper().gotoWorker();
    app.getWorkerHelper().addWorker();
    app.getWorkerHelper().fillWorkerForm(new WorkerData( "Алёша","Абакумов", "eee+" + Math.round(Math.random() * 100) + "@gmail.com", "Тренер", null, null, null, null, null, null, null, "89035550415", null, null, null, null, null, null, null, null));
    app.getWorkerHelper().submitWorkerCreation();
  }
}
