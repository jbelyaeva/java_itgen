package tests.chat;

import core.general.RunTestAgain;
import app.testbase.TestBase;
import org.testng.annotations.Test;

public class ChatOpenCloseTest extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatOpenClose() {
    app.goTo().menuTasks();
    app.chat().openCloseChat();
  }
}
