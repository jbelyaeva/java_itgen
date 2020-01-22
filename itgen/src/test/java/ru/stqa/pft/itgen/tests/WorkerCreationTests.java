package ru.stqa.pft.itgen.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.WorkerData;

public class WorkerCreationTests extends TestBase {

  @Test
  public void testWorkerCreation() throws Exception {
    app.getNavigationHelper().gotoWorker();
    app.getWorkerHelper().addWorker();
    app.getWorkerHelper().fillWorkerForm(new WorkerData("0Иванов", "eee+" + Math.round(Math.random() * 10) + "@mail.ru", "1111111111111", "Администратор", "Иван"));
    app.getWorkerHelper().submitAdminCreation();
  }
}
