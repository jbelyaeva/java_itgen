package tests.lkStudent;
/*T-249
Нельзя найти родителя
 */
import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatSearchDialogWithRandomParentTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    String messageOld = "Привет";
    data.chat().set1_DialogStudentTrainer(messageOld, "21", "23");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatSearchDialogWithRandomParent() {
    app.chat().btnOpenChat();
    String record = app.chat().searchBadPersonByUser("Родитель");
    app.check().equalityOfTwoElements(record, "Ничего не нашли...");
    app.chat().btnCloseChat();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().chat();
  }
}
