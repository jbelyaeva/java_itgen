package tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import core.general.RunTestAgain;
import app.testbase.TestBase;
import org.testng.annotations.Test;

public class TrainerChatWithAdminByDefoltTest extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTrainerChatWithAdminByDefolt() {
    app.chat().btnOpenChat();
    assertThat(app.chat().chatByDefault(), equalTo("Айтигеник"));
    app.chat().btnCloseChat();
  }
}
