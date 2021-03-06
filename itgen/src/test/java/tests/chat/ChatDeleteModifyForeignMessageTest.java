package tests.chat;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatDeleteModifyForeignMessageTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    String messageOld = "Привет";
    data.chat().set3_DialogSuperTrainer(messageOld);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatDeleteModifyForeignMessage() {
    assertThat(app.chat().deleteForeignMessage("23"), equalTo(false));
    app.chat().btnCloseChat();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().chat();
  }
}
