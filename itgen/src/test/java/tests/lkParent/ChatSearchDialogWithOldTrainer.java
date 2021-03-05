package tests.lkParent;
/* T-87 */
/* В дефолтную семью добавлен ученик в статусе Будет пробное
/* У родителя есть диалог с дефолтным тренером
/* Открыть чат и проверить, что через поиск можно найти диалог с этим тренером
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatSearchDialogWithOldTrainer extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    String period = "19:00 - 21:00";
    data.defFamilyChat()
        .set1_DialogWithTrainer_StudentAddInDefaultFamily_TrialWillBeTomorrow(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatSearchDialogWithOldTrainer() {
    app.student().refresh();
    app.chat().btnOpenChat();
    String[] etalon = {"Дефолтный Тренер"};
    String[] dialogs = app.chat().searchPersonByUser("Тренер");

    app.check().equalityOfTwoElements(dialogs.length, 1);
    app.check().equalityOfTwoElements(dialogs, etalon);
    app.chat().btnCloseChat();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().chat().taskAndSchedule().student();
  }
}
