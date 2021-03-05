package tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerChatSearchDialogWithEmployersTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.newWorker().set1_NewWorker();
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
    data.clean().worker();
  }
}
