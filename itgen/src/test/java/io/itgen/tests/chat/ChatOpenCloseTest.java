package io.itgen.tests.chat;

import io.itgen.general.RunTestAgain;
import io.itgen.tests.TestBase;
import org.testng.annotations.Test;

public class ChatOpenCloseTest extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatOpenClose() {
    app.goTo().menuTasks();
    app.chat().openCloseChat();
  }
}
