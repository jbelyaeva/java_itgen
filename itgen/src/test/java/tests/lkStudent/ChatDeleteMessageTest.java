package tests.lkStudent;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.chat.ChatMessages;
import data.model.chat.ChatRooms;
import data.model.chat.ChatSubscriptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*T-378
 * Есть диалог студента с тренером. Студент отправил сллбщение. Зайти под студентом в этот диалог и удалить
 * это сообщение. Проверить,что сообщение удалилось и у студента, и у тренера
 */
public class ChatDeleteMessageTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    String messageOld = "Привет";
    data.chat().set1_DialogStudentTrainer(messageOld, "21", "23");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatDeleteMessage() {
    app.base().refresh();
    ChatMessages beforeMessage = app.dbchat().chatMessages();
    ChatRooms beforeRooms = app.dbchat().chatRooms();
    ChatSubscriptions beforeSubscription = app.dbchat().chatSubscriptions();

    app.chat().deleteMessageByStudent();
    Boolean messageGetTrainer = app.chat()
        .getByTrainerDeletedMessageFromStudent("trainer", "111111");

    ChatMessages afterMessage = app.dbchat().chatMessages();
    ChatRooms afterRooms = app.dbchat().chatRooms();
    ChatSubscriptions afterSubscription = app.dbchat().chatSubscriptions();

    app.check().equalityOfTwoElements(afterMessage.size(), beforeMessage.size());
    app.check().equalityOfTwoElements(afterRooms.size(), beforeRooms.size());
    app.check().equalityOfTwoElements(afterSubscription.size(), beforeSubscription.size());
    app.check().equalityOfTwoElements(messageGetTrainer, false);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().chat();
  }
}
