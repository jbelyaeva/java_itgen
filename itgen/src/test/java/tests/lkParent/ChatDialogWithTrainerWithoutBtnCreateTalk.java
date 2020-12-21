package tests.lkParent;
/* T-91 */
/* В дефолтную семью добавлен ученик в статусе Будет пробное
/* У родителя есть диалог с тренером
/* Открыть чат и проверить, что нет кнопки Создать беседу
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatDialogWithTrainerWithoutBtnCreateTalk extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    String period = "19:00 - 21:00";
    data.defFamilyChat()
        .set1_DialogWithTrainer_StudentAddInDefaultFamily_TrialWillBeTomorrow(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatDialogWithTrainerWithoutBtnCreateTalk() {
    app.student().refresh();
    app.chat().btnOpenChat();
    app.check().notFindElement(app.chat().getBtnCreateTalk());
    app.chat().btnCloseChat();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().chat().taskAndSchedule().student();
  }
}
