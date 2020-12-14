package tests.lkParent;
/* T-84 */
/* Ребенок без пробного. Открыть диалог и проверить, что диалог только с айтигеником.
 */

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.Test;

public class ChatDialogOnlyWithGenaTest extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatDialogOnlyWithGena() {
    String[] dialogs = app.chat().getDialogs();
    assertThat(dialogs.length, equalTo(1));
    assertThat(dialogs[0], equalTo("Айтигеник"));
    app.chat().btnCloseChat();
  }
}
