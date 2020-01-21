package ru.stqa.pft.itgen.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.AdminData;

public class WorkerCreationTest extends TestBase {

  @Test
  public void testWorkerCreation() throws Exception {
    app.getNavigationHelper().gotoWorker();
    app.getAdminHelper().addWorker();
    app.getAdminHelper().fillAdminForm(new AdminData("0Иванов", "eee+" + Math.round(Math.random() * 10) + "@mail.ru", "1111111111111", "Администратор", "Иван"));
    app.getAdminHelper().submitAdminCreation();
  }
}
