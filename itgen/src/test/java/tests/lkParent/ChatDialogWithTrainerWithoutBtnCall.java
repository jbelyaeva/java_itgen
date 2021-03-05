package tests.lkParent;
/* T-90 */
/* В дефолтную семью добавлен ученик в статусе Будет пробное
/* У родителя есть диалог с тренером
/* Открыть чат и проверить, что нет кнопки Позвонить
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatDialogWithTrainerWithoutBtnCall extends TestBase {
private final String period = "19:00 - 21:00";
  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamilyChat()
        .set1_DialogWithTrainer_StudentAddInDefaultFamily_TrialWillBeTomorrow(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatDialogWithTrainerWithoutBtnCall() {
    app.student().refresh();
    app.chat().btnOpenChat();
    app.chat().searchPerson("Тренер");
    app.chat().clickByFirstMessage();
    app.check().notFindElement(app.chat().getBtnCall());
    app.chat().btnCloseChat();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().chat().taskAndSchedule().student();
  }
}
