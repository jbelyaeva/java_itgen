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
    String period = "21.00 : 23.00";
    data.clean().chat().taskAndSchedule();
    data.defFamily().set22_SingleLessonTomorrowWithDefaultStudent(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatModifyMessage() throws InterruptedException {
    String messageOld = "Привет";
    String messageNew = "Пока";
    ChatMessages beforeMessage = app.dbchat().chatMessages();
    ChatRooms beforeRooms = app.dbchat().chatRooms();
    ChatSubscriptions beforeSubscription = app.dbchat().chatSubscriptions();

    app.chat().btnOpenChat();
    app.chat().chooseSecondWithUser();
    app.lkStudent().sendMessageToTrainer(messageOld);
    app.chat().btnCloseChat();
    app.chat().modifyMessageByStudentForTrainer(messageNew);
    app.check().textElement(app.chat().getTextMessageAlong(), "Пока");
    app.chat().btnCloseChat();
    ChatMessages afterMessage = app.dbchat().chatMessages();
    ChatRooms afterRooms = app.dbchat().chatRooms();
    ChatSubscriptions afterSubscription = app.dbchat().chatSubscriptions();

    app.check().equalityOfTwoElements(afterMessage.size(), beforeMessage.size() + 1);
    app.check().equalityOfTwoElements(afterRooms.size(), beforeRooms.size() + 1);
    app.check().equalityOfTwoElements(afterSubscription.size(), beforeSubscription.size() + 2);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().chat();
    app.base().refresh();
  }
}
