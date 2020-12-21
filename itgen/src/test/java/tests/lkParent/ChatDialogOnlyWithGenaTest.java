package tests.lkParent;
/* T-84 */
/* Ребенок без пробного. Открыть диалог и проверить, что диалог только с айтигеником.
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.Test;

public class ChatDialogOnlyWithGenaTest extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatDialogOnlyWithGena() {
    String[] dialogs = app.chat().getDialogs();
    app.check().equalityOfTwoElements(dialogs.length, 1);
    app.check().equalityOfTwoElements(dialogs[0], "Айтигеник");
    app.chat().btnCloseChat();
  }
}
