package tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.Test;

public class TrainerChatWithAdminByDefaultTest extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTrainerChatWithAdminByDefolt() {
    app.chat().btnOpenChat();
    assertThat(app.chat().chatByDefault(), equalTo("Айтигеник"));
    app.chat().btnCloseChat();
  }
}
