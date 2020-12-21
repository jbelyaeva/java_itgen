package tests.lkParent;
/* T-86 */
/* Ребенок записан на пробное с тренером Бокша и Дефолтным тренером.
/* От Дефолтного тренера  есть новое сообщение родителю
/* Открыть диалог и проверить, что диалог с айтигеником закреплен сверху
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatFirstDialogWithGena extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamilyChat().set2_DialogWithTrainer1_TwoTrialWillBeTomorrowWithTrainer1AndTrainer2();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatFirstDialogWithGena() {
    app.lkParent().reset();
    String[] etalon = {"Айтигеник", "Дефолтный Тренер", "Бокша Настя"};
    String[] dialogs = app.chat().getDialogs();
    app.chat().btnCloseChat();
    app.check().equalityOfTwoElements(dialogs.length, 3);
    app.check().equalityOfTwoElements(dialogs, etalon);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().chat().taskAndSchedule().student();
  }
}
