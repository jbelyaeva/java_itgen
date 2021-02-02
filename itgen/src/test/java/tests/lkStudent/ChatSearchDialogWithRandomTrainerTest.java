package tests.lkStudent;
/*T-248
 *Найти в поиске тренера, которого нет в расписании ученика - не найдет
 *Есть диалог с тренером, первый начал студент
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatSearchDialogWithRandomTrainerTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    String messageOld = "Привет";
    data.chat().set1_DialogStudentTrainer(messageOld, "21", "23");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatSearchDialogWithRandomTrainer() {
    app.chat().btnOpenChat();
    String record = app.chat().searchBadPersonByUser("Бокша");
    app.check().equalityOfTwoElements(record, "Ничего не нашли...");
    app.chat().btnCloseChat();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().chat();
  }
}
