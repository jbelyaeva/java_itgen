package ru.stqa.pft.itgen.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.WorkerData;

public class WorkerAndTrainerCreationTests extends TestBase {

  @Test
  public void testWorkerCreation() {
    app.getNavigationHelper().gotoWorker();
    app.getWorkerHelper().addWorker();
    app.getWorkerHelper().fillWorkerForm(new WorkerData("Павел", "Абакумов", "eee+" + Math.round(Math.random() * 10) + "@gmail.com", "89035540414", "Тренер"));
    app.getWorkerHelper().submitWorkerCreation();
  }
}
