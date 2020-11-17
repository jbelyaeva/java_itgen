package tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import data.services.WorkerService;
import app.testbase.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerChatSearchDialogWithEmployeersTest extends TestBase {
  WorkerService workerService = new WorkerService();

  @BeforeMethod
  public void ensurePreconditions() {
    app.trWorker()
        .saveNewWorker(
            "worker",
            "Маша",
            "Машина",
            "employee",
            "AL",
            "Europe/Minsk",
            "ru",
            "ru",
            "1234567899",
            "julja83@list.ru");
  }

  @Test
  public void testTrainerChatSearchDialogWithAdmin() {
    app.chat().btnOpenChat();
    assertThat(app.chat().searchPerson("AdminFirst"), equalTo(false));
    app.chat().btnCloseChat();
  }

  @Test
  public void testTrainerChatSearchDialogWithWorker() {
    app.chat().btnOpenChat();
    assertThat(app.chat().searchPerson("Машина"), equalTo(false));
    app.chat().btnCloseChat();
  }

  @Test
  public void testTrainerChatSearchDialogWithTrainer() {
    app.chat().btnOpenChat();
    assertThat(app.chat().searchPerson("Бокша"), equalTo(false));
    app.chat().btnCloseChat();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    workerService.DeleteById("worker");
  }
}
