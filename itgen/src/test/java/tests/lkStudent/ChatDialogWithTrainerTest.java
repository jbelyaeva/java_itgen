package tests.lkStudent;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*T-245
 *Открыть чат, если есть диалоги и новое сообщение от тренера - диалог с айтигеником закреплен
 * (Сообщение от ученика тренеру. Проверка, что айтигеник первый, тренер второй)
 */
public class ChatDialogWithTrainerTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.postClean().chat();
    String messageOld = "Привет";
    data.chat().set1_DialogStudentTrainer(messageOld, "21", "23");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatDialogWithTrainer() {
    app.base().refresh();
    String[] etalon = {"Айтигеник", "Дефолтный Тренер"};
    String[] dialogs = app.chat().getDialogs();
    app.chat().btnCloseChat();
    app.check().equalityOfTwoElements(dialogs.length, 2);
    app.check().equalityOfTwoElements(dialogs, etalon);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().chat();
  }
}
