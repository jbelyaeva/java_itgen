package io.itgen.tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.general.RunTestAgain;
import io.itgen.tests.TestBase;
import org.testng.annotations.Test;

public class TrainerChatWithAdminByDefoltTest extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTrainerChatWithAdminByDefolt() {
    app.chat().btnOpenChat();
    assertThat(app.chat().chatByDefolt(),equalTo("Айтигеник"));
    app.chat().btnCloseChat();
  }
}
