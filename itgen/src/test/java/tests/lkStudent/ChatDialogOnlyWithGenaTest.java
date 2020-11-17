package tests.lkStudent;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.Test;

public class ChatDialogOnlyWithGenaTest extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatDialogOnlyWithGena() {
    app.student().refresh();
    String[] dialogs = app.chat().getDialogs();
    assertThat(dialogs.length, equalTo(1));
    assertThat(dialogs[0], equalTo("Айтигеник"));
    app.chat().btnCloseChat();
  }
}
