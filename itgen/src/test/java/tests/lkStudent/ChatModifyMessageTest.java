package tests.lkStudent;
/*T-253
 * у ученика есть диалог с тренером, где первым написал ученик. Можно это сообщение отредактировать
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.chat.ChatMessages;
import data.model.chat.ChatRooms;
import data.model.chat.ChatSubscriptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatModifyMessageTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.postClean().chat();
    String messageOld = "Привет";
    data.chat().set1_DialogStudentTrainer(messageOld, "21", "23");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatModifyMessage() {
    String messageNew = "Пока";
    ChatMessages beforeMessage = app.dbchat().chatMessages();
    ChatRooms beforeRooms = app.dbchat().chatRooms();
    ChatSubscriptions beforeSubscription = app.dbchat().chatSubscriptions();

    app.chat().modifyMessageByStudent(messageNew);
    String messageGetTrainer = app.chat().getByTrainerMessageFromStudent("trainer", "111111");

    ChatMessages afterMessage = app.dbchat().chatMessages();
    ChatRooms afterRooms = app.dbchat().chatRooms();
    ChatSubscriptions afterSubscription = app.dbchat().chatSubscriptions();

    app.check().equalityOfTwoElements(afterMessage.size(), beforeMessage.size());
    app.check().equalityOfTwoElements(afterRooms.size(), beforeRooms.size());
    app.check().equalityOfTwoElements(afterSubscription.size(), beforeSubscription.size());
    app.check().equalityOfTwoElements(messageNew, messageGetTrainer);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().chat();
  }
}
