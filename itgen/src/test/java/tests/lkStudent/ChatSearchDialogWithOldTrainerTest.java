package tests.lkStudent;
/*T-246
 * Есть диалог с тренером. Первый начал студент. Можно найти в поиске тренера, который есть в списке диалогов
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatSearchDialogWithOldTrainerTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    String messageOld = "Привет";
    data.chat().set1_DialogStudentTrainer(messageOld, "21", "23");
  }


  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatSearchDialogWithOldTrainer() {
    app.base().refresh();
    app.chat().btnOpenChat();
    String[] etalon = {"Дефолтный Тренер"};
    String[] dialogs = app.chat().searchPersonByUser("Дефолтный");
    app.check().equalityOfTwoElements(dialogs.length, 1);
    app.check().equalityOfTwoElements(dialogs, etalon);
    app.chat().btnCloseChat();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().chat();
  }
}
