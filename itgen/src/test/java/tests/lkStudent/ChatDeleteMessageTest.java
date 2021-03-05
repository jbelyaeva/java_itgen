package tests.lkStudent;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.chat.ChatMessages;
import data.model.chat.ChatRooms;
import data.model.chat.ChatSubscriptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*T-378
 * Есть диалог студента с тренером. Студент отправил сooбщение. Зайти под студентом в этот диалог и удалить
 * это сообщение. Проверить,что сообщение удалилось у студента
 * по информации от разработчика: метод получения одинаковый для всех, следовательно проверкой, что не отображается
 * под тренером можно принебречь и проверить только руками один раз
 */
public class ChatDeleteMessageTest extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatDeleteMessage() {
    ChatMessages beforeMessage = app.dbchat().chatMessages();
    ChatRooms beforeRooms = app.dbchat().chatRooms();
    ChatSubscriptions beforeSubscription = app.dbchat().chatSubscriptions();
    app.chat().btnOpenChat();
    app.chat().chooseFirstWithUser();
    app.lkStudent().sendMessageToTrainer("Привет");
    app.chat().btnCloseChat();
    app.chat().deleteMessageByStudent();

    ChatMessages afterMessage = app.dbchat().chatMessages();
    ChatRooms afterRooms = app.dbchat().chatRooms();
    ChatSubscriptions afterSubscription = app.dbchat().chatSubscriptions();

    app.check().equalityOfTwoElements(afterMessage.size(), beforeMessage.size() + 1);
    app.check().equalityOfTwoElements(afterRooms.size(), beforeRooms.size() + 1);
    app.check().equalityOfTwoElements(afterSubscription.size(), beforeSubscription.size() + 2);
    app.check().fieldNotNull(app.dbchat().lastMessage().getRemovedAt());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().chat();
  }
}
