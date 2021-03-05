package tests.chat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatDeleteForeignMessageTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.chat().set3_DialogSuperTrainer("привет");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatDeleteForeignMessage() {
    app.check().equalityOfTwoElements(app.chat().deleteForeignMessage("23"), false);
    app.chat().btnCloseChat();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().chat();
    app.base().refresh();
  }
}
