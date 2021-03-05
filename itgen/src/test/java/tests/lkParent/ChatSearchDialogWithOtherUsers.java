package tests.lkParent;
/* T-89 */
/* В дефолтную семью добавлен ученик в статусе Будет пробное
/* У родителя есть диалог с Дефолтным тренером
/* Есть еще семья : родитель (Родитель Новый)+ученик(Олег Олегов)
/* Есть сотрудник (Sem Semov)
/* Открыть чат и проверить, что через поиск нельзя админа/сотрудника/др родителя/др ученика
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatSearchDialogWithOtherUsers extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
   String period = "19:00 - 21:00";
    data.defFamilyChat()
        .set1_DialogWithTrainer_StudentAddInDefaultFamily_TrialWillBeTomorrow(period);
    data.studentService()
        .deleteField(data.studentService().findById("newStudent").getId(), "username");
    data.newFamily().set1_FamilyWithStudentAndParent();
    data.newWorker().set1_NewWorker();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatSearchDialogWithEmployer() {
    app.student().refresh();
    app.chat().btnOpenChat();
    String record = app.chat().searchBadPersonByUser("Semov");
    app.check().equalityOfTwoElements(record, "Ничего не нашли...");
    app.chat().btnCloseChat();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatSearchDialogWithAdmin() {
    app.student().refresh();
    app.chat().btnOpenChat();
    String record = app.chat().searchBadPersonByUser("Admin");
    app.check().equalityOfTwoElements(record, "Ничего не нашли...");
    app.chat().btnCloseChat();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatSearchDialogWithOtherParent() {
    app.student().refresh();
    app.chat().btnOpenChat();
    String record = app.chat().searchBadPersonByUser("Родитель");
    app.check().equalityOfTwoElements(record, "Ничего не нашли...");
    app.chat().btnCloseChat();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatSearchDialogWithOtherStudent() {
    app.student().refresh();
    app.chat().btnOpenChat();
    String record = app.chat().searchBadPersonByUser("Олегов");
    app.check().equalityOfTwoElements(record, "Ничего не нашли...");
    app.chat().btnCloseChat();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().chat().taskAndSchedule().student().parent().worker().family();
  }
}
