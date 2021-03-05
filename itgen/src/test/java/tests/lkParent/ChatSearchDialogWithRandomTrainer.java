package tests.lkParent;
/* T-88 */
/* В дефолтную семью добавлен ученик в статусе Будет пробное
/* У родителя есть диалог с Дефолтным тренером
/* Открыть чат и проверить, что через поиск нельзя тренера2
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatSearchDialogWithRandomTrainer extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    String period = "19:00 - 21:00";
    data.defFamilyChat()
        .set1_DialogWithTrainer_StudentAddInDefaultFamily_TrialWillBeTomorrow(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatSearchDialogWithRandomTrainer() {
    app.student().refresh();
    app.chat().btnOpenChat();
    String record = app.chat().searchBadPersonByUser("Бокша");
    app.check().equalityOfTwoElements(record, "Ничего не нашли...");
    app.chat().btnCloseChat();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().chat().taskAndSchedule().student();
  }
}
